/**
 * 
 */
package com.udacity.jwdnd.course1.cloudstorage.models.dtos;

/**
 * @author utkarsh
 *
 */
public class FileDetailDto {
	
	private String fileName;
	private byte[] fileData;
	private String contentType;
	private Long fileSize;
	
	public FileDetailDto() {
		//empty constructor
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getFileData() {
		return fileData;
	}

	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
}
