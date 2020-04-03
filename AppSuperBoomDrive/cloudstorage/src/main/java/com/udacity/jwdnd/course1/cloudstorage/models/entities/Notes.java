/**
 * 
 */
package com.udacity.jwdnd.course1.cloudstorage.models.entities;

/**
 * @author utkarsh
 *
 */
public class Notes {
	
	private Long noteId;
	private String noteTitle;
	private String noteDescription;
	private Long userId;
	
	public Notes(String noteTitle, String noteDescription) {
		super();
		this.noteTitle = noteTitle;
		this.noteDescription = noteDescription;
	}
	
	public Notes() {
		
	}

	public Long getNoteId() {
		return noteId;
	}

	public void setNoteId(Long noteId) {
		this.noteId = noteId;
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
