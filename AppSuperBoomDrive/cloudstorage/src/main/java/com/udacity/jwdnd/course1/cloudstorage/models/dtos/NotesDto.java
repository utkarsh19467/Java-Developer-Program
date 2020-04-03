/**
 * 
 */
package com.udacity.jwdnd.course1.cloudstorage.models.dtos;

/**
 * @author utkarsh
 *
 */
public class NotesDto {
	
	private Long noteId;
	private String noteTitle;
	private String noteDescription;
	
	public NotesDto() {
		
	}
	
	public NotesDto(String nodeTitle, String noteDescription) {
		this.noteTitle = nodeTitle;
		this.noteDescription = noteDescription;
	}
	
	public String getNoteTitle() {
		return noteTitle;
	}
	
	public void setNoteTitle(String noteTitle) {
		this.noteTitle = noteTitle;
	}
	
	public String getNoteDescription() {
		return noteDescription;
	}
	
	public void setNoteDescription(String noteDescription) {
		this.noteDescription = noteDescription;
	}

	public Long getNoteId() {
		return noteId;
	}

	public void setNoteId(Long noteId) {
		this.noteId = noteId;
	}
}
