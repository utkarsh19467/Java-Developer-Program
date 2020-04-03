/**
 * 
 */
package com.udacity.jwdnd.course1.cloudstorage.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.udacity.jwdnd.course1.cloudstorage.models.entities.Credentials;

/**
 * @author utkarsh
 *
 */
@Mapper
public interface CredentialsMapper {

	@Insert("INSERT INTO CREDENTIALS(url, username, password, key, userid) " +
	        " VALUES (#{url}, #{username}, #{password}, #{key}, #{userId})")
	public int save(Credentials credentials);
	
	@Delete("DELETE FROM CREDENTIALS WHERE credentialid =#{credentialId}")
	public void deleteCredentialById(Long credentialId);
	
	@Select("SELECT * FROM CREDENTIALS WHERE credentialid =#{credentialId}")
	public Credentials getCredentialById(Long credentialId);
	
	@Update("UPDATE CREDENTIALS SET url=#{url}, "
			+ "username=#{username}, "
			+ "password=#{password} "
			+ "where credentialid=#{credentialId}")
	public void updateCredentialById(Credentials credentials);
}
