/**
 * 
 */
package com.udacity.jwdnd.course1.cloudstorage.models.entities;

/**
 * @author utkarsh
 *
 */
public class Credentials {
	
	private Long credentialId;
	private String url;
	private String username;
	private String key;
	private String password;
	private Long userId;
	
	public Credentials() {
		
	}
	
	public Credentials(String url, String username, String key, String password) {
		
	}

	public Long getCredentialId() {
		return credentialId;
	}

	public void setCredentialId(Long credentialId) {
		this.credentialId = credentialId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
