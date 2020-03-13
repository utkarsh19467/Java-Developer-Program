/**
 * 
 */
package com.udacity.course3.reviews.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.course3.reviews.entities.Comment;
import com.udacity.course3.reviews.entities.Review;
import com.udacity.course3.reviews.entities.ReviewDocument;
import com.udacity.course3.reviews.repositories.CommentRepository;
import com.udacity.course3.reviews.repositories.ReviewMongoRepository;
import com.udacity.course3.reviews.repositories.ReviewRepository;

/**
 * @author utkarsh
 *
 */
@Service
public class CommentPersistenceService {
    static Logger logger = LogManager.getLogger(CommentPersistenceService.class);

    @Autowired
    private ReviewRepository reviewRepo;

    @Autowired
    private ReviewMongoRepository reviewMongoRepo;

    @Autowired
    private CommentRepository commentRepo;

    public Optional<Comment> persistComment(Integer reviewId, Comment comment) {
	Optional<Comment> commentEntity = Optional.empty();

	Optional<Review> review = reviewRepo.findById(reviewId);

	if (review.isPresent()) {
	    comment.setReview(review.get());
	    commentRepo.save(comment);

	    List<ReviewDocument> reviewDocumentList = reviewMongoRepo.findAllByReviewId(Integer.toString(reviewId));
	    ReviewDocument reviewDocument = reviewDocumentList.get(0);

	    List<Comment> comments = reviewDocument.getComments();
	    if (comments == null) {
		comments = new ArrayList<Comment>();
	    }
	    comments.add(comment);
	    reviewDocument.setComments(comments);
	    reviewMongoRepo.save(reviewDocument);

	    commentEntity = Optional.of(comment);
	} else {
	    logger.info("Review Document with Id not found!");
	}
	return commentEntity;
    }
}
