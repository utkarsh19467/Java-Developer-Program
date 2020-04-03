/**
 * 
 */
package com.udacity.jwdnd.course1.cloudstorage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.udacity.jwdnd.course1.cloudstorage.models.entities.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.models.entities.File;
import com.udacity.jwdnd.course1.cloudstorage.models.entities.Notes;
import com.udacity.jwdnd.course1.cloudstorage.models.entities.UserInfo;

/**
 * @author utkarsh
 *
 */
@Mapper
public interface AllDetailsMapper {

	@Select("SELECT userid, firstname, lastname, username, password FROM USERS where username = #{username}")
    @Results(value = {
    	@Result(property="userId", column = "userid"),	
        @Result(property="firstName", column = "firstname"),
        @Result(property="lastName", column = "lastname"),
        @Result(property="username", column = "username"),
        @Result(property="password", column = "password"),
        @Result(property="files", column="userid", javaType= List.class, many=@Many(select="getAllFiles")),
        @Result(property="notes", column="userid", javaType= List.class, many=@Many(select="getAllNotes")),
        @Result(property="credentials", column="userid", javaType= List.class, many=@Many(select="getAllCredentials"))
    })   
	public UserInfo findByUsernameWithAllDetails(String username);
	
	@Select("SELECT * FROM FILES WHERE userid = #{userId}")
    @Results(value={
        @Result(property="fileId", column ="fileid" ),
        @Result(property="fileName", column = "filename"),
        @Result(property="fileData", column = "filedata"),
        @Result(property="contentType", column = "contenttype"),
        @Result(property="fileSize", column = "filesize"),
        @Result(property="userId", column = "userid")
    })
	List<File> getAllFiles(Long userId);
	
	@Select("SELECT * FROM NOTES WHERE userid = #{userId}")
    @Results(value={
        @Result(property="noteId", column ="noteid" ),
        @Result(property="noteTitle", column = "notetitle"),
        @Result(property="noteDescription", column = "notedescription"),
        @Result(property="userId", column = "userid")
    })
	List<Notes> getAllNotes(Long userId);
	
	@Select("SELECT * FROM CREDENTIALS WHERE userid = #{userId}")
    @Results(value={
        @Result(property="credentialId", column ="credentialid" ),
        @Result(property="url", column = "url"),
        @Result(property="username", column = "username"),
        @Result(property="password", column = "password"),
        @Result(property="userId", column = "userid")
    })
	List<Credentials> getAllCredentials(Long userId);
}
