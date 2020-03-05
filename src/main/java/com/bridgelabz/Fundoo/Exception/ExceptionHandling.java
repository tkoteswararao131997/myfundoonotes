package com.bridgelabz.Fundoo.Exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bridgelabz.Fundoo.Response.UserResponse;

@ControllerAdvice
public class ExceptionHandling{
	
	@ExceptionHandler(UserExceptions.class)
	public ResponseEntity<ExceptionResponse> userCustomExceptions(UserExceptions ex)
	{
		ExceptionResponse res=new ExceptionResponse();
		res.setMessage(ex.getMessage());
		res.setData(ex.getData());
		res.setStatus(ex.getStatus());
		return ResponseEntity.status(res.getStatus()).body(new ExceptionResponse(res.getMessage(),res.getData(),res.getStatus()));
	}
}
