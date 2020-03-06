package com.bridgelabz.Fundoo.Exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bridgelabz.Fundoo.Response.Response;

@ControllerAdvice
public class ExceptionHandling{
	
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ExceptionResponse> userCustomExceptions(CustomException ex)
	{
		ExceptionResponse res=new ExceptionResponse();
		res.setMessage(ex.getMessage());
		res.setData(ex.getData());
		res.setStatus(ex.getStatus());
		return ResponseEntity.status(res.getStatus()).body(new ExceptionResponse(res.getMessage(),res.getData(),res.getStatus()));
	}
}
