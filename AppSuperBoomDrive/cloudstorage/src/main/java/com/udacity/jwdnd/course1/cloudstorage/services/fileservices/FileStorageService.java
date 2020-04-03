/**
 * 
 */
package com.udacity.jwdnd.course1.cloudstorage.services.fileservices;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.dtos.FileDetailDto;
import com.udacity.jwdnd.course1.cloudstorage.models.dtos.FilesDto;
import com.udacity.jwdnd.course1.cloudstorage.models.entities.File;
import com.udacity.jwdnd.course1.cloudstorage.services.modelconvertors.FileConvertor;

/**
 * @author utkarsh
 *
 */
@Service
public class FileStorageService {

	@Autowired
	private FileMapper fileMapper;
	
	@Autowired
	private FileConvertor fileConvertor;

	public void uploadFile(FilesDto filesDto) throws IOException{
		fileMapper.save(fileConvertor.fileDtoToFile(filesDto));
	}
	
	public FileDetailDto findByFileName(String fileName) {
		File file = fileMapper.findByFileName(fileName);
		FileDetailDto fileDetailDto = null;
		if(file != null) {
			fileDetailDto = fileConvertor.fileToFileDetailDto(file);
		}
		return fileDetailDto;
	}
	
	public void deleteFile(Long fileId) {
		fileMapper.deleteByFileId(fileId);
	}
}
