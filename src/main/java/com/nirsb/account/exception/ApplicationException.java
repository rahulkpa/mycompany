package com.nirsb.account.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.nirsb.account.model.UserResponse;

@ControllerAdvice
public class ApplicationException extends ResponseEntityExceptionHandler{
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> handleUserNotFount(Exception ex, WebRequest request){
		UserResponse errorResponse = new UserResponse("FAILED", new Date(), ex.getMessage(), request.getContextPath());
		return new ResponseEntity<Object>(errorResponse, HttpStatus.NOT_FOUND);
	}
}
