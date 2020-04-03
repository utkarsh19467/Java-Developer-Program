/**
 * 
 */
package com.udacity.jwdnd.course1.cloudstorage.services.notesservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NotesMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.dtos.NotesDto;
import com.udacity.jwdnd.course1.cloudstorage.models.entities.Notes;
import com.udacity.jwdnd.course1.cloudstorage.services.modelconvertors.NotesConvertor;

/**
 * @author utkarsh
 *
 */
@Service
public class NotesService {
	
	@Autowired
	private NotesMapper notesMapper;

	@Autowired
	private NotesConvertor notesConvertor;
	
	public void addNote(NotesDto notesDto) {
		Notes notes = notesMapper.findNoteById(notesDto.getNoteId());
		if(notes != null) {
			notes = notesConvertor.noteDataUpdate(notes, notesDto);
			notesMapper.updateNoteById(notes);
		} else {
			notesMapper.save(notesConvertor.notesDtoToNote(notesDto));
		}
	}
	
	public void deleteNote(Long noteId) {
		notesMapper.deleteNoteById(noteId);
	}
	
	public NotesDto getNote(Long noteId) {
		return notesConvertor.notesToNotesDto(notesMapper.findNoteById(noteId));
	}
}
