package com.bridgelabz.Fundoo.Dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class CollaboratorDto {
	@NotBlank
	@Email
	private String colabEmail;
}
