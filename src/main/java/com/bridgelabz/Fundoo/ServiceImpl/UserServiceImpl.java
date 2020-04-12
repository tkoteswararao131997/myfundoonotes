package com.bridgelabz.Fundoo.ServiceImpl;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.bridgelabz.Fundoo.Dto.ForgotPwdDto;
import com.bridgelabz.Fundoo.Dto.LoginDto;
import com.bridgelabz.Fundoo.Dto.UpdatePwdDto;
import com.bridgelabz.Fundoo.Dto.UserDto;
import com.bridgelabz.Fundoo.Entity.UserEntity;
import com.bridgelabz.Fundoo.Exception.CustomException;
import com.bridgelabz.Fundoo.Repository.UserRepository;
import com.bridgelabz.Fundoo.Service.UserServiceInf;
import com.bridgelabz.Fundoo.Utility.JwtOperations;

@Service
@CrossOrigin
public class UserServiceImpl implements UserServiceInf {
	@Autowired
	private UserRepository userrepo;
	@Autowired
	private BCryptPasswordEncoder pwdencoder;
	@Autowired
	private JwtOperations jwt=new JwtOperations();
	//@Autowired
	//private RabbitMQSender mailsender;
	//public static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	@Override
	public UserEntity registerUser(UserDto dto)
	{
		isEmailExists(dto.getEmail());
		UserEntity entity=new UserEntity();
		BeanUtils.copyProperties(dto, entity);
		entity.setCreateDate(LocalDateTime.now());
		entity.setUpdateDate(LocalDateTime.now());
		entity.setPassword(pwdencoder.encode(entity.getPassword()));
		UserEntity res =userrepo.save(entity);
		//log.info(entity.getName()+" registered "+"date:"+entity.getCreateDate());
		String body="http://localhost:8080/verifyemail/"+jwt.jwtToken(entity.getUserid());
		jwt.sendEmail(entity.getEmail(),"verification email",body);
		//mailsender.sendMessage(new Notification(entity.getEmail(),"verification"));
		return entity;
	}
	@Override
	public String loginUser(LoginDto dto) {
		UserEntity user=userrepo.getUserByEmail(dto.getEmail()).orElseThrow(() -> new CustomException("login failed",HttpStatus.OK,null,"false"));
		boolean ispwd=pwdencoder.matches(dto.getPassword(),user.getPassword());
		if(ispwd==false || user.isVerifyEmail()==false) {
			throw new CustomException("login failed",HttpStatus.OK,null,"false");
		} else {
			String token=jwt.jwtToken(user.getUserid());
			return token;
			
		}

	}
	@Override
	public List<UserEntity> getall()
	{
		return userrepo.getAllUsers().orElseThrow(() -> new CustomException("no users present", HttpStatus.OK,null,"false"));
	}
	@Override
	public UserEntity getUserByEmail(String email) {
	UserEntity user=userrepo.getUserByEmail(email).orElseThrow(() -> new CustomException("email not exists",HttpStatus.OK,null,"false"));
	return user;
	}
	
	@Override
	public UserEntity verify(String token) {
		long id=jwt.parseJWT(token);
		UserEntity user=userrepo.isIdExists(id).orElseThrow(() -> new CustomException("user not exists",HttpStatus.OK,id,"false"));
		user.setVerifyEmail(true);
		userrepo.save(user);
		return user;
	}
	@Override
	public UserEntity getUserById(long id) {
		return userrepo.getUserById(id).orElseThrow(() -> new CustomException("user not exists",HttpStatus.OK,id,"false"));
	}
	@Override
	public boolean isIdPresent(long id) {
		UserEntity user=userrepo.getUserById(id).orElseThrow(() -> new CustomException("user not exists",HttpStatus.OK,id,"false"));
		if(user.getEmail()!=null)
		return true;
		return false;
	}
	public void deleteUser(long userId) {
			UserEntity user=getUserById(userId);
			userrepo.delete(user);
	}
	@Override
	public boolean isEmailExists(String email) {
		if(userrepo.isEmailExists(email).isPresent())
			throw new CustomException("email already exists",HttpStatus.OK,null,"false");
		return false;
	}
	@Override
	public UserEntity updatepwd(UpdatePwdDto pwddto) {
			long userid=jwt.parseJWT(pwddto.getToken());
			UserEntity user=getUserById(userid);
			user.setPassword(pwdencoder.encode(pwddto.getNewpassword()));
			userrepo.save(user);
			return user;
	}
	@Override
	public String forgotPwd(ForgotPwdDto forgotdto) {
		UserEntity user=getUserByEmail(forgotdto.getEmail());
		String body="http://localhost:4200/resetpassword/"+jwt.jwtToken(user.getUserid());
		jwt.sendEmail(user.getEmail(),"Reset Password",body);
		return "success";
	}
	
}
