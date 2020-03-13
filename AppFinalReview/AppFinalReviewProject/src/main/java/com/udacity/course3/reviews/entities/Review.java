/**
 * 
 */
package com.udacity.course3.reviews.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author utkarsh
 *
 */
@Entity
@Table(name = "review")
@JsonIgnoreProperties
public class Review {
    /** The id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** The headline. */
    private String header;

    /** The content. */
    private String content;

    /** The product. */
    @ManyToOne
    private Product product;

    /**
     * Instantiates a new review.
     */
    public Review() {
    }

    /**
     * Instantiates a new review.
     *
     * @param id the id
     */
    public Review(Integer id) {
	this.id = id;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public Integer getId() {
	return id;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(Integer id) {
	this.id = id;
    }

    /**
     * Gets the headline.
     *
     * @return the headline
     */
    public String getHeader() {
	return header;
    }

    /**
     * Sets the headline.
     *
     * @param headline the new headline
     */
    public void setHeader(String header) {
	this.header = header;
    }

    /**
     * Gets the content.
     *
     * @return the content
     */
    public String getContent() {
	return content;
    }

    /**
     * Sets the content.
     *
     * @param content the new content
     */
    public void setContent(String content) {
	this.content = content;
    }

    /**
     * Gets the product.
     *
     * @return the product
     */
    public Product getProduct() {
	return product;
    }

    /**
     * Sets the product.
     *
     * @param product the new product
     */
    public void setProduct(Product product) {
	this.product = product;
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
	return "Review [headline=" + header + ", content=" + content + ", product=" + product + "]";
    }
}
