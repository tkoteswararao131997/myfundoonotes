package com.bridgelabz.Fundoo.Dto;
import lombok.Data;

@Data
public class UserDto {
	private String name;
	private String password;
	private String mobileNumber;
	private String email;
	public String getName() {
		return name;
	}
}