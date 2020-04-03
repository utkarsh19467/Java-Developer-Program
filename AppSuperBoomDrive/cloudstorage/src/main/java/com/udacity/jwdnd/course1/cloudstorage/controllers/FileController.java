/**
 * 
 */
package com.udacity.jwdnd.course1.cloudstorage.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.udacity.jwdnd.course1.cloudstorage.models.dtos.FileDetailDto;
import com.udacity.jwdnd.course1.cloudstorage.models.dtos.FilesDto;
import com.udacity.jwdnd.course1.cloudstorage.services.fileservices.FileStorageService;

/**
 * @author utkarsh
 *
 */
@Controller
public class FileController {

	@Autowired
	private FileStorageService fileStorageService;
	
	@ModelAttribute("file")
	public FilesDto getFilesDto() {
		return new FilesDto();
	}
	
	@PostMapping(value = "/uploadFile")
	public String uploadFile(@ModelAttribute("file") FilesDto filesDto, BindingResult result) throws IOException{
		try {
			fileStorageService.uploadFile(filesDto);
		} catch(Exception exception) {
			return "redirect:/result?failure";
		}
		return "redirect:/result?success";
	}
	
	@GetMapping(value = "/deleteFile/{fileId}")
	public String deleteFile(@PathVariable(name = "fileId") String fileID) {
		Long fileId = Long.parseLong(fileID);
		fileStorageService.deleteFile(fileId);
		return "redirect:/result?deleteSuccess";
	}
	
	@GetMapping(value = "/downloadFile/{fileName}")
	public ResponseEntity downloadFile(@PathVariable(name = "fileName") String fileName) {
		FileDetailDto dto = fileStorageService.findByFileName(fileName);
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType("application/octet-stream"))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
				.body(dto.getFileData());
	}
}
