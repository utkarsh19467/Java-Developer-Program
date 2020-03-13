/**
 * 
 */
package com.udacity.course3.reviews.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.udacity.course3.reviews.entities.Comment;
import com.udacity.course3.reviews.entities.Review;

/**
 * @author utkarsh
 *
 */
@Repository
public interface CommentRepository extends CrudRepository<Comment, Integer> {
	
	/**
	 * Find all by review.
	 *
	 * @param review the review
	 * @return the list of review
	 */
	List<Comment> findAllByReview(Review review);
}
