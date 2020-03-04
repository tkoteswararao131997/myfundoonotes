package com.bridgelabz.Fundoo.Response;

import com.bridgelabz.Fundoo.Entity.UserEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponse {
	
	String message;
	int status;
	UserEntity user;
	public UserResponse(String message,UserEntity user,int status) 
	{
		this.message=message;
		this.status=status;
		this.user=user;
	}
	public UserResponse(String message, int status) {
		this.message=message;
		this.status=status;
	}
	
	

}
