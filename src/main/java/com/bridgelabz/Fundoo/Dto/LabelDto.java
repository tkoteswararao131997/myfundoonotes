package com.bridgelabz.Fundoo.Dto;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class LabelDto {
	@Size(min=1,max=10)
	private String labelName;

}
