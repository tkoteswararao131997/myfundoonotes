package com.bridgelabz.Fundoo.Service;

import java.util.List;
import java.util.Optional;

import com.bridgelabz.Fundoo.Dto.LoginDto;
import com.bridgelabz.Fundoo.Dto.UpdatePwdDto;
import com.bridgelabz.Fundoo.Dto.UserDto;
import com.bridgelabz.Fundoo.Entity.UserEntity;

public interface UserServiceInf {
	UserEntity registerUser(UserDto dto);
	UserEntity getUserByEmail(String email);
	List<UserEntity> getall();
	UserEntity verify(String token);
	UserEntity getUserById(long userId);
	boolean isIdPresent(long id);
	boolean isEmailExists(String email);
	String loginUser(LoginDto dto);
	UserEntity updatepwd(UpdatePwdDto pwddto);

}
