package com.udacity.course3.reviews.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.udacity.course3.reviews.entities.ReviewDocument;

/**
 * ReviewMongoDB repository.
 * 
 * @author utkarsh
 *
 */
public interface ReviewMongoRepository extends MongoRepository<ReviewDocument, String> {
	
	List<ReviewDocument> findAllByReviewId(String reviewId);
	
	List<ReviewDocument> findAllByProductId(String productId);
}
