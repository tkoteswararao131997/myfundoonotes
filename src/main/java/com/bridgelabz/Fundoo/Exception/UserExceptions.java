package com.bridgelabz.Fundoo.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UserExceptions extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String message;
	private HttpStatus status;
	private Object data;
	public UserExceptions(String message,HttpStatus status,Object data) {
		this.status=status;
		this.message=message;
		this.data=data;
		
	}
}