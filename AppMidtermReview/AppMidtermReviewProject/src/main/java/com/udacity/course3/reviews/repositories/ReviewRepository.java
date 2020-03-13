/**
 * 
 */
package com.udacity.course3.reviews.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.udacity.course3.reviews.entities.Product;
import com.udacity.course3.reviews.entities.Review;

/**
 * @author utkarsh
 *
 */
@Repository
public interface ReviewRepository extends CrudRepository<Review, Integer> {
	/**
	 * Find all review related to a product
	 * 
	 * @param product
	 * @return
	 */
	List<Review> findAllByProduct(Product product);
}
