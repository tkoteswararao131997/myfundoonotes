//package com.bridgelabz.Fundoo.ServiceImpl;
//
//import java.time.LocalDateTime;
//
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//
//import com.bridgelabz.Fundoo.Dto.LabelDto;
//import com.bridgelabz.Fundoo.Entity.LabelEntity;
//import com.bridgelabz.Fundoo.Entity.UserEntity;
//import com.bridgelabz.Fundoo.Exception.UserExceptions;
//import com.bridgelabz.Fundoo.Repository.LabelRepository;
//import com.bridgelabz.Fundoo.Repository.UserRepository;
//import com.bridgelabz.Fundoo.Service.LabelServiceInf;
//import com.bridgelabz.Fundoo.Utility.JwtOperations;
//
//@Service
//public class LabelServiceImpl implements LabelServiceInf 
//{
//	@Autowired
//	private LabelRepository labelrepo;
//	@Autowired
//	private LabelEntity labelentity;
//	@Autowired
//	private UserEntity userentity;
//	@Autowired
//	private UserRepository userrepo;
//	@Autowired
//	private JwtOperations jwt;
//	@Autowired
//	private UserServiceImpl userimpl;
//	@Override
//	public String createLabel(LabelDto labeldto,String token) {
//		isLabelExists(labeldto.getLabelName());
//		long id=jwt.parseJWT(token);
//		UserEntity user=userimpl.getUserById(id);
//		labelentity.setLabelName(labeldto.getLabelName());
//		labelentity.setCreateDate(LocalDateTime.now());
//		labelentity.setUpdateDate(LocalDateTime.now());
//		userentity.getLabels().add(labelentity);
//		userrepo.save(userentity);
//		return labeldto.getLabelName();
//	}
//	private void isLabelExists(String labelName) {
//		boolean b=labelrepo.isLabelExists(labelName).isPresent();
//		if(b==true)
//			throw new UserExceptions("label already exists",HttpStatus.NOT_ACCEPTABLE,null);
//		
//	}
//
//}
