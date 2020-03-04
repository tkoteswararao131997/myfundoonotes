package com.bridgelabz.Fundoo.Response;

import java.util.List;

import com.bridgelabz.Fundoo.Entity.UserEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponse {
	
	String message;
	int status;
	Object data;
		public UserResponse(String message,UserEntity user,int status) 
	{
		this.message=message;
		this.status=status;
		this.data=user;
	}
	public UserResponse(String message, int status) {
		this.message=message;
		this.status=status;
	}
	public UserResponse(String message2, List<UserEntity> users, int status2) {
		this.message=message2;
		this.status=status2;
		this.data=users;
	}
	
	

}
