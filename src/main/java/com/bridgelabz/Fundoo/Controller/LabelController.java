//package com.bridgelabz.Fundoo.Controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.bridgelabz.Fundoo.Dto.LabelDto;
//import com.bridgelabz.Fundoo.Entity.LabelEntity;
//import com.bridgelabz.Fundoo.Response.UserResponse;
//import com.bridgelabz.Fundoo.ServiceImpl.LabelServiceImpl;
//import com.bridgelabz.Fundoo.ServiceImpl.UserServiceImpl;
//import com.bridgelabz.Fundoo.Utility.JwtOperations;
//
//@RestController
//public class LabelController {
//	
//	@Autowired
//	private LabelServiceImpl labelimpl;
//	@Autowired
//	private UserServiceImpl userimpl;
//	private JwtOperations jwt=new JwtOperations();
//	
//	@PostMapping("/addlabel/{token}")
//	public ResponseEntity<UserResponse> addLabel(@PathVariable LabelDto dto,@PathVariable("token") String token)
//	{
//		long id=jwt.parseJWT(token);
//		boolean isuser=userimpl.isIdPresent(id);
//		if(dto.getLabelName()!=null && isuser==true)
//		{
//			LabelEntity label=labelimpl.addLabel(dto);
//			return new ResponseEntity<UserResponse>(new UserResponse("label added", label, 201),HttpStatus.ACCEPTED);
//		}
//		return new ResponseEntity<UserResponse>(new UserResponse("user not found(or) label should not be empty",null,406),HttpStatus.NOT_ACCEPTABLE);
//		
//	}
//}
