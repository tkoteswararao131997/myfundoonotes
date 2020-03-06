package com.bridgelabz.Fundoo.Dto;

import lombok.Data;

@Data
public class UpdatePwdDto {
	private String email;
	private String oldpassword;
	private String newpassword;
	private String conformpassword;
	

}
