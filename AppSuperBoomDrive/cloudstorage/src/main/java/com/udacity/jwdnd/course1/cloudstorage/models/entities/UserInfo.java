/**
 * 
 */
package com.udacity.jwdnd.course1.cloudstorage.models.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * @author utkarsh
 *
 */
public class UserInfo {
	
	private Long userId;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String salt;
	private List<File> files = new ArrayList<>();
	private List<Notes> notes = new ArrayList<>();
	private List<Credentials> credentials = new ArrayList<>();
	
	public UserInfo() {
		
	}
	
	public UserInfo(String firstName, String lastName, String username, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userid) {
		this.userId = userid;
	}

	public List<File> getFiles() {
		return files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}

	
	public List<Notes> getNotes() {
		return notes;
	}

	public void setNotes(List<Notes> notes) {
		this.notes = notes;
	}

	public List<Credentials> getCredentials() {
		return credentials;
	}

	public void setCredentials(List<Credentials> credentials) {
		this.credentials = credentials;
	}

	@Override
	public String toString() {
		return "UserInfo [id=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", username=" + username
				+ ", password=" + password + ", salt=" + salt + "]";
	}
}
