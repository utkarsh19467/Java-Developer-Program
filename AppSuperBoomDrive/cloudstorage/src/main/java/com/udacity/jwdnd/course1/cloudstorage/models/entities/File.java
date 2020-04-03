/**
 * 
 */
package com.udacity.jwdnd.course1.cloudstorage.models.entities;

import java.util.Arrays;

/**
 * @author utkarsh
 *
 */
public class File {
	
	private Long fileId;
	private String fileName;
	private byte[] fileData;
	private String contentType;
	private Long fileSize;
	private Long userId;
	
	public File() {
		
	}

	public File(String fileName, byte[] fileData, String contentType, Long fileSize) {
		this.fileName = fileName;
		this.fileData = fileData;
		this.contentType = contentType;
		this.fileSize = fileSize;
	}
	
	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "File [fileId=" + fileId + ", fileName=" + fileName + ", fileData=" + Arrays.toString(fileData)
				+ ", contentType=" + contentType + ", fileSize=" + fileSize + ", userId=" + userId + "]";
	}
	
}
