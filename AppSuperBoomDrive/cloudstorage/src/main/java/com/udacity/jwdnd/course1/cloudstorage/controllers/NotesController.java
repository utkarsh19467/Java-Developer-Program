/**
 * 
 */
package com.udacity.jwdnd.course1.cloudstorage.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.udacity.jwdnd.course1.cloudstorage.models.dtos.NotesDto;
import com.udacity.jwdnd.course1.cloudstorage.services.notesservices.NotesService;

/**
 * @author utkarsh
 *
 */
@Controller
public class NotesController {
	
	@Autowired
	private NotesService notesService;

	@ModelAttribute("note")
	public NotesDto getNotesDto() {
		return new NotesDto();
	}
	
	@PostMapping(value = "/addNote") 
	public String addNote(@ModelAttribute("note") NotesDto notesDto) {
		try {
			notesService.addNote(notesDto);
		} catch(Exception exception) {
			return "redirect:/result?failure";
		}
		return "redirect:/result?success";
	}
	
	@GetMapping(value = "/deleteNote/{noteId}")
	public String deleteNote(@PathVariable(name = "noteId") String noteID) {
		Long noteId = Long.parseLong(noteID);
		notesService.deleteNote(noteId);
		return "redirect:/result?deleteSuccess";
	}
	
	@GetMapping(value = "/getNote/{noteId}")
	@ResponseBody
	public NotesDto getNote(@PathVariable(name = "noteId") String noteID) {
		Long noteId = Long.parseLong(noteID);
		return notesService.getNote(noteId);
	}
}
