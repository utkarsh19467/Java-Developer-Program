package com.udacity.course3.reviews.services;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.course3.reviews.entities.Product;
import com.udacity.course3.reviews.entities.Review;
import com.udacity.course3.reviews.entities.ReviewDocument;
import com.udacity.course3.reviews.repositories.ProductRepository;
import com.udacity.course3.reviews.repositories.ReviewMongoRepository;
import com.udacity.course3.reviews.repositories.ReviewRepository;

/**
 * The ReviewPersistence Service.
 * 
 * @author utkarsh
 *
 */
@Service
public class ReviewPersistenceService {

    static Logger logger = LogManager.getLogger(CommentPersistenceService.class);

    @Autowired
    ProductRepository productRepo;

    @Autowired
    ReviewMongoRepository reviewMongoRepo;

    @Autowired
    ReviewRepository reviewRepo;

    public ReviewDocument entityToDocConvertor(Integer productId, Review review) {
	return new ReviewDocument(Integer.toString(review.getId()), review.getHeader(), review.getContent(),
		Integer.toString(productId), null);
    }

    /**
     * Persists Review with JPARepo and MongoRepo using productId
     */
    public Optional<ReviewDocument> persistReview(Integer productId, Review review) {
	Optional<ReviewDocument> persistedReview = Optional.empty();
	Optional<Product> product = productRepo.findById(productId);

	if (product.isPresent()) {
	    review.setProduct(product.get());
	    reviewRepo.save(review);

	    ReviewDocument reviewDoc = entityToDocConvertor(productId, review);
	    persistedReview = Optional.of(reviewMongoRepo.save(reviewDoc));
	    return persistedReview;
	} else {
	    logger.info("Product with passed Id not present.");
	    return persistedReview;
	}
    }

    public Optional<List<ReviewDocument>> getReviewDocForProduct(Integer productId) {
	Optional<List<ReviewDocument>> reviewDoc = Optional.empty();
	try {
	    List<ReviewDocument> reviewList = reviewMongoRepo.findAllByProductId(Integer.toString(productId));
	    reviewDoc = Optional.of(reviewList);
	} catch (Exception e) {
	    logger.info("Unable to fetch review doucment for product Id");
	}
	return reviewDoc;
    }
}