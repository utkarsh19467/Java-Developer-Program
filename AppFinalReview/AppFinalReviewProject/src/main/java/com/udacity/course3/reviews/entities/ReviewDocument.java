package com.udacity.course3.reviews.entities;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 
 * Review Document for MangoDB
 * 
 * @author utkarsh
 */
@Document(collection = "review")
public class ReviewDocument {

    @Id
    private String id;

    private String reviewId;

    private String header;

    private String content;

    private String productId;

    List<Comment> comments;

    public ReviewDocument() {
    }

    public ReviewDocument(String reviewId, String header, String content, String productId, List<Comment> comments) {
	super();
	this.reviewId = reviewId;
	this.header = header;
	this.content = content;
	this.productId = productId;
	this.comments = comments;
    }

    public ReviewDocument(String id, String reviewId, String header, String content, Integer productId,
	    List<Comment> comments) {
	super();
	this.id = id;
	this.header = header;
	this.content = content;
	this.productId = Integer.toString(productId);
	this.comments = comments;
    }

    public String getReviewId() {
	return reviewId;
    }

    public void setReviewId(String reviewId) {
	this.reviewId = reviewId;
    }

    public String getheader() {
	return header;
    }

    public void setheader(String header) {
	this.header = header;
    }

    public String getContent() {
	return content;
    }

    public void setContent(String content) {
	this.content = content;
    }

    public String getProductId() {
	return productId;
    }

    public void setProductId(String productId) {
	this.productId = productId;
    }

    public List<Comment> getComments() {
	return comments;
    }

    public void setComments(List<Comment> comments) {
	this.comments = comments;
    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }
}
