package com.bridgelabz.Fundoo.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.bridgelabz.Fundoo.Entity.LabelEntity;
import com.bridgelabz.Fundoo.Entity.NoteEntity;
import com.bridgelabz.Fundoo.Entity.UserEntity;
import com.bridgelabz.Fundoo.Utility.JwtOperations;
@Configuration
public class AppConfig {
	@Bean
	public BCryptPasswordEncoder bcryptpasswordencoder()
	{
		return new BCryptPasswordEncoder();
	}
	@Bean
	public JwtOperations jwtoperations()
	{
		return new JwtOperations();
	}
	@Bean
	public UserEntity userentity()
	{
		return new UserEntity();
	}
	@Bean
	public NoteEntity noteentity()
	{
		return new NoteEntity();
	}
	@Bean
	public LabelEntity labelentity()
	{
		return new LabelEntity();
	}
}
