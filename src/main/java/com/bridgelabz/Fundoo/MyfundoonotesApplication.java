package com.bridgelabz.Fundoo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class MyfundoonotesApplication{
	public static void main(String[] args) {
		SpringApplication.run(MyfundoonotesApplication.class, args);
	}
}
