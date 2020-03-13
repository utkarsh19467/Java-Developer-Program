package com.udacity.course3.reviews.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udacity.course3.reviews.entities.Comment;
import com.udacity.course3.reviews.entities.Review;
import com.udacity.course3.reviews.repositories.CommentRepository;
import com.udacity.course3.reviews.services.CommentPersistenceService;

/**
 * Spring REST controller for working with comment entity.
 */
@RestController
@RequestMapping("/comments")
public class CommentsController {

    @Autowired
    CommentRepository commentRepo;

    @Autowired
    CommentPersistenceService commentPersistenceService;

    /**
     * Creates a comment for a review.
     *
     * 1. Add argument for comment entity. Use {@link RequestBody} annotation. 2.
     * Check for existence of review. 3. If review not found, return NOT_FOUND. 4.
     * If found, save comment.
     *
     * @param reviewId The id of the review.
     */
    @PostMapping(value = "/reviews/{reviewId}")
    public ResponseEntity<?> createCommentForReview(@PathVariable("reviewId") Integer reviewId,
	    @RequestBody Comment comment) {
        
	if(comment.getheader() == null || comment.getContent() == null) {
	    return ResponseEntity.badRequest().build();
	}
	Optional<Comment> persistedComment = commentPersistenceService.persistComment(reviewId, comment);
	if (persistedComment.isPresent()) {
	    return ResponseEntity.ok(persistedComment.get());
	} else {
	    return ResponseEntity.notFound().build();
	}
    }

    /**
     * List comments for a review.
     *
     * 2. Check for existence of review. 3. If review not found, return NOT_FOUND.
     * 4. If found, return list of comments.
     *
     * @param reviewId The id of the review.
     */
    @GetMapping(value = "/reviews/{reviewId}")
    public ResponseEntity<List<?>> listCommentsForReview(@PathVariable("reviewId") Integer reviewId) {
	List<Comment> commentList = commentRepo.findAllByReview(new Review(reviewId));
	if (commentList.isEmpty()) {
	    return ResponseEntity.notFound().build();
	} else {
	    return ResponseEntity.ok(commentList);
	}
    }
}