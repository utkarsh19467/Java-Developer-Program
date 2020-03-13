/**
 * 
 */
package com.example.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.model.requests.ErrorResponse;

/**
 * @author utkarsh
 *
 */
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(Exception exception){
	ErrorResponse response = new ErrorResponse();
	response.setStatus(HttpStatus.BAD_REQUEST.value());
	response.setMessage(exception.getMessage());
	response.setTimeStamp(System.currentTimeMillis());
	
	return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
