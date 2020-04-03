/**
 * 
 */
package com.udacity.jwdnd.course1.cloudstorage.services.modelconvertors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.udacity.jwdnd.course1.cloudstorage.models.dtos.NotesDto;
import com.udacity.jwdnd.course1.cloudstorage.models.entities.Notes;
import com.udacity.jwdnd.course1.cloudstorage.services.userservices.AuthenticatedUserUtility;

/**
 * @author utkarsh
 *
 */
@Component
public class NotesConvertor {
	
	@Autowired
	private AuthenticatedUserUtility userUtility;

	public Notes notesDtoToNote(NotesDto notesDto) {
		Notes notes = new Notes();
		notes.setNoteDescription(notesDto.getNoteDescription());
		notes.setNoteTitle(notesDto.getNoteTitle());
		notes.setUserId(userUtility.getAuthenticatedUser().getUserId());
		return notes;
	}
	
	public NotesDto notesToNotesDto(Notes notes) {
		NotesDto notesDto = new NotesDto();
		notesDto.setNoteDescription(notes.getNoteDescription());
		notesDto.setNoteTitle(notes.getNoteTitle());
		notesDto.setNoteId(notes.getNoteId());
		return notesDto;
	}
	
	public Notes noteDataUpdate(Notes notes, NotesDto notesDto) {
		notes.setNoteTitle(notesDto.getNoteTitle());
		notes.setNoteDescription(notesDto.getNoteDescription());
		return notes;
	}
}
