package com.udacity.course3.reviews.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.udacity.course3.reviews.entities.Product;
import com.udacity.course3.reviews.entities.Review;
import com.udacity.course3.reviews.repositories.ProductRepository;
import com.udacity.course3.reviews.repositories.ReviewRepository;

/**
 * Spring REST controller for working with review entity.
 */
@RestController
public class ReviewsController {

    /** The review repo. */
    @Autowired
    private ReviewRepository reviewRepo;

    /** The product repo. */
    @Autowired
    private ProductRepository productRepo;

    /**
     * Creates a review for a product.
     * <p>
     * 1. Add argument for review entity. Use {@link RequestBody} annotation. 2.
     * Check for existence of product. 3. If product not found, return NOT_FOUND. 4.
     * If found, save review.
     *
     * @param productId The id of the product.
     * @param review    the review
     * @return The created review or 404 if product id is not found.
     */
    @PostMapping(value = "/reviews/products/{productId}")
    public ResponseEntity<?> createReviewForProduct(@PathVariable("productId") Integer productId,
	    @RequestBody Review review) {
	
	//when posting empty review I'm returning bad request.
	
	if (review.getContent() == null || review.getHeader() == null) {
	    return ResponseEntity.badRequest().build();
	}
	Optional<Product> product = productRepo.findById(productId);
	if (product.isPresent()) {
	    review.setProduct(product.get());
	    return ResponseEntity.ok(reviewRepo.save(review));
	} else {
	    return ResponseEntity.notFound().build();
	}
    }

    /**
     * Lists reviews by product.
     *
     * @param productId The id of the product.
     * @return The list of reviews.
     */
    @GetMapping(value = "/reviews/products/{productId}")
    public ResponseEntity<List<?>> listReviewsForProduct(@PathVariable("productId") Integer productId) {
	List<Review> reviewList = reviewRepo.findAllByProduct(new Product(productId));
	if (reviewList.isEmpty()) {
	    return ResponseEntity.notFound().build();
	} else {
	    return ResponseEntity.ok(reviewList);
	}
    }
}