/**
 * 
 */
package com.udacity.course3.reviews.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The Class Product.
 *
 * @author utkarsh
 */
@Entity
@Table(name = "product")
@JsonIgnoreProperties
public class Product {
    /** The id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** The price. */
    private Integer price;

    /** The name. */
    private String name;

    /** The about product. */
    private String about;

    /**
     * Instantiates a new product.
     */
    public Product() {
    }

    /**
     * Instantiates a new product.
     *
     * @param id the id
     */
    public Product(Integer id) {
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
     * Gets the price.
     *
     * @return the price
     */
    public Integer getPrice() {
	return price;
    }

    /**
     * Sets the price.
     *
     * @param price the new price
     */
    public void setPrice(Integer price) {
	this.price = price;
    }

    /**
     * Gets the about product.
     *
     * @return the about
     */
    public String getabout() {
	return about;
    }

    /**
     * Sets the about product.
     *
     * @param about the about to set
     */
    public void setabout(String about) {
	this.about = about;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
	return name;
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(String name) {
	this.name = name;
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
	return "Product [price=" + price + ", name=" + name + ", about product=" + about + "]";
    }

}
