package com.bridgelabz.Fundoo.ServiceImpl;
import java.time.LocalDateTime;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.bridgelabz.Fundoo.Dto.ForgotPwdDto;
import com.bridgelabz.Fundoo.Dto.LoginDto;
import com.bridgelabz.Fundoo.Dto.UpdatePwdDto;
import com.bridgelabz.Fundoo.Dto.UserDto;
import com.bridgelabz.Fundoo.Entity.UserEntity;
import com.bridgelabz.Fundoo.Exception.CustomException;
import com.bridgelabz.Fundoo.Repository.UserRepository;
import com.bridgelabz.Fundoo.Service.UserServiceInf;
import com.bridgelabz.Fundoo.Utility.JwtOperations;
import com.bridgelabz.Fundoo.Utility.MailService;
import com.bridgelabz.Fundoo.Utility.Notification;
import com.bridgelabz.Fundoo.Utility.RabbitMQSender;

@Service
public class UserServiceImpl implements UserServiceInf {
	@Autowired
	private UserRepository userrepo;
	@Autowired
	private BCryptPasswordEncoder pwdencoder;
	@Autowired
	private JwtOperations jwt=new JwtOperations();
	@Autowired
	private RabbitMQSender mailsender;
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
		mailsender.sendMessage(new Notification(entity.getEmail(),"verification"));
		return entity;
	}
	@Override
	public String loginUser(LoginDto dto) {
		UserEntity user=userrepo.getUserByEmail(dto.getEmail()).orElseThrow(() -> new CustomException("login failed",HttpStatus.NOT_FOUND,null));
		boolean ispwd=pwdencoder.matches(dto.getPassword(),user.getPassword());
		if(ispwd==false || user.isVerifyEmail()==false)
			throw new CustomException("login failed",HttpStatus.NOT_FOUND,null);
		String token=jwt.jwtToken(user.getUserid());
		return token;
		
	}
	@Override
	public List<UserEntity> getall()
	{
		return userrepo.getAllUsers().orElseThrow(() -> new CustomException("no users present", HttpStatus.NOT_FOUND,null));
	}
	@Override
	public UserEntity getUserByEmail(String email) {
	UserEntity user=userrepo.getUserByEmail(email).orElseThrow(() -> new CustomException("email not exists",HttpStatus.BAD_REQUEST,null));
	return user;
	}
	
	@Override
	public UserEntity verify(String token) {
		long id=jwt.parseJWT(token);
		UserEntity user=userrepo.isIdExists(id).orElseThrow(() -> new CustomException("user not exists",HttpStatus.BAD_REQUEST,id));
		user.setVerifyEmail(true);
		userrepo.save(user);
		return user;
	}
	@Override
	public UserEntity getUserById(long id) {
		return userrepo.getUserById(id).orElseThrow(() -> new CustomException("user not exists",HttpStatus.BAD_REQUEST,id));
	}
	@Override
	public boolean isIdPresent(long id) {
		UserEntity user=userrepo.getUserById(id).orElseThrow(() -> new CustomException("user not exists",HttpStatus.BAD_REQUEST,id));
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
			throw new CustomException("email already exists",HttpStatus.NOT_ACCEPTABLE,null);
		return false;
	}
	@Override
	public UserEntity updatepwd(UpdatePwdDto pwddto) {
		UserEntity user=getUserByEmail(pwddto.getEmail());
		if((pwddto.getNewpassword().equals(pwddto.getConformpassword()) && (pwdencoder.matches(pwddto.getOldpassword(),user.getPassword()))))
		{
			user.setPassword(pwdencoder.encode(pwddto.getConformpassword()));
			userrepo.save(user);
			return user;
		}
		else {
			throw new CustomException("password not matching",HttpStatus.NOT_ACCEPTABLE,null);
		}
	}
	public String forgotPwd(ForgotPwdDto forgotdto) {
		UserEntity user=getUserByEmail(forgotdto.getEmail());
		if(user.getUserid()==forgotdto.getUserid() && forgotdto.getNewpassword().equals(forgotdto.getConformpassword()))
		{
			user.setPassword(pwdencoder.encode(forgotdto.getConformpassword()));
			userrepo.save(user);
			jwt.sendEmail(user.getEmail(),"your new password",forgotdto.getConformpassword());
			return forgotdto.getConformpassword();
		}
		else
		{
			throw new CustomException("enter valid details",HttpStatus.NOT_ACCEPTABLE,null);
		}
	}
	
}
