/**
 * 
 */
package com.udacity.jwdnd.course1.cloudstorage.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.udacity.jwdnd.course1.cloudstorage.models.entities.Notes;

/**
 * @author utkarsh
 *
 */
@Mapper
public interface NotesMapper {

	@Insert("INSERT INTO NOTES(notetitle, notedescription, userid) " +
	        " VALUES (#{noteTitle}, #{noteDescription}, #{userId})")
	public int save(Notes note);
	
	@Delete("DELETE FROM NOTES WHERE noteid =#{noteId}")
	public void deleteNoteById(Long noteId);
	
	@Select("SELECT * FROM NOTES WHERE noteid = #{noteId}")
	public Notes findNoteById(Long noteId);
	
	@Update("UPDATE NOTES SET notetitle=#{noteTitle}, "
			+ "notedescription=#{noteDescription} "
			+ "where noteid=#{noteId}")
	public void updateNoteById(Notes note);
}
