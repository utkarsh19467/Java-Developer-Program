/**
 * 
 */
package com.udacity.jwdnd.course1.cloudstorage.models.dtos;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author utkarsh
 *
 */
public class FilesDto {

	private MultipartFile file;
	
	public FilesDto() {
		
	}
	
	public FilesDto(MultipartFile file) {
		this.file = file;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
}
