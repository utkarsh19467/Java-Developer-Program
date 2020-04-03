package com.udacity.jwdnd.course1.cloudstorage.services.modelconvertors;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.udacity.jwdnd.course1.cloudstorage.models.dtos.FileDetailDto;
import com.udacity.jwdnd.course1.cloudstorage.models.dtos.FilesDto;
import com.udacity.jwdnd.course1.cloudstorage.models.entities.File;
import com.udacity.jwdnd.course1.cloudstorage.services.userservices.AuthenticatedUserUtility;

/**
 * The Class FileConvertor.
 */
@Component
public class FileConvertor {
	
	@Autowired
	private AuthenticatedUserUtility userUtility;
	
	public File fileDtoToFile(FilesDto filesDto) throws IOException{
		File file = new File();
		file.setFileName(filesDto.getFile().getOriginalFilename());
		file.setFileData(filesDto.getFile().getBytes());
		file.setContentType(filesDto.getFile().getContentType());
		file.setFileSize(filesDto.getFile().getSize());
		file.setUserId(userUtility.getAuthenticatedUser().getUserId());
		return file;
	}
	
	public FileDetailDto fileToFileDetailDto(File file) {
		FileDetailDto fileDetailDto = new FileDetailDto();
		fileDetailDto.setFileName(file.getFileName());
		fileDetailDto.setFileSize(file.getFileSize());
		fileDetailDto.setFileData(file.getFileData());
		fileDetailDto.setContentType(file.getContentType());
		
		return fileDetailDto;
	}
}
