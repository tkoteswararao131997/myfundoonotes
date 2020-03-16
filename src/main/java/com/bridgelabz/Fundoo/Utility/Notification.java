package com.bridgelabz.Fundoo.Utility;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class Notification implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String notificationType;
    private String msg;
}
