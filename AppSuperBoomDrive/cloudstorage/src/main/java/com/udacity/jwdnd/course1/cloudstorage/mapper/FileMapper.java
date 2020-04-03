/**
 * 
 */
package com.udacity.jwdnd.course1.cloudstorage.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.udacity.jwdnd.course1.cloudstorage.models.entities.File;

/**
 * @author utkarsh
 *
 */
@Mapper
public interface FileMapper {

	@Insert("INSERT INTO FILES(filename, filedata, filesize, contenttype, userid) " +
	        " VALUES (#{fileName}, #{fileData}, #{fileSize}, #{contentType}, #{userId})")
	public int save(File file);
	
	@Select("SELECT * FROM FILES WHERE filename = #{fileName}")
	public File findByFileName(String fileName);
	
	@Delete("DELETE FROM FILES WHERE fileid =#{fileId}")
	public void deleteByFileId(Long fileId);
	
}
