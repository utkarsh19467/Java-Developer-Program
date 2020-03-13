/**
 * 
 */
package com.udacity.course3.reviews.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.udacity.course3.reviews.entities.Product;

/**
 * @author utkarsh
 *
 */
@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
}
