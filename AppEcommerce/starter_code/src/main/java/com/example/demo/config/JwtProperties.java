/**
 * 
 */
package com.example.demo.config;

/**
 * The Class JwtProperties.
 *
 * @author utkarsh
 */
public class JwtProperties {

	/**
	 * Instantiates a new jwt properties.
	 */
	private JwtProperties() {

	}

	/** The Constant SECRET. */
	public static final String SECRET = "TokenToGenerateJwt";
	
	/** The Constant EXPIRATION_TIME. */
	public static final long EXPIRATION_TIME = 864000000; // 10 days
	
	/** The Constant TOKEN_PREFIX. */
	public static final String TOKEN_PREFIX = "Bearer ";
	
	/** The Constant HEADER_STRING. */
	public static final String HEADER_STRING = "Authorization";

}
