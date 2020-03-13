package com.example.demo.model.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.persistence.EcommerceUser;

/**
 * The Interface UserRepository.
 */
public interface UserRepository extends JpaRepository<EcommerceUser, Long> {
	
	/**
	 * Find by username.
	 *
	 * @param username the username
	 * @return the ecommerce user
	 */
	EcommerceUser findByUsername(String username);
}
