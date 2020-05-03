package com.bridgelabz.Fundoo.Utility;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MailService implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String consumerMail;
	private String message;
	private String body;
}

