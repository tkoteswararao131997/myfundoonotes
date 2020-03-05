//package com.bridgelabz.Fundoo.ServiceImpl;
//
//import java.time.LocalDateTime;
//
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.bridgelabz.Fundoo.Dto.LabelDto;
//import com.bridgelabz.Fundoo.Entity.LabelEntity;
//import com.bridgelabz.Fundoo.Entity.UserEntity;
//import com.bridgelabz.Fundoo.Service.LabelServiceInf;
//
//@Service
//public class LabelServiceImpl implements LabelServiceInf {
//	@Autowired
//	private UserServiceImpl userservice;
//	private LabelEntity labelentity=new LabelEntity();
//	@Override
//	public LabelEntity addLabel(LabelDto dto) {
//			BeanUtils.copyProperties(dto, labelentity);
//			labelentity.setCreateDate(LocalDateTime.now());
//			labelentity.setUpdateDate(LocalDateTime.now());
//			uses
//		return null;
//	}
//
//}
