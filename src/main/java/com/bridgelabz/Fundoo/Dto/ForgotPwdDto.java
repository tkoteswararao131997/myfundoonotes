package com.bridgelabz.Fundoo.Dto;

import lombok.Data;

@Data
public class ForgotPwdDto {
	private String email;
	private long userid;
	private String newpassword;
	private String conformpassword;

}
