package com.bridgelabz.Fundoo.ServiceImpl;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bridgelabz.Fundoo.Dto.LabelDto;
import com.bridgelabz.Fundoo.Entity.LabelEntity;
import com.bridgelabz.Fundoo.Entity.NoteEntity;
import com.bridgelabz.Fundoo.Entity.UserEntity;
import com.bridgelabz.Fundoo.Exception.CustomException;
import com.bridgelabz.Fundoo.Repository.LabelRepository;
import com.bridgelabz.Fundoo.Repository.UserRepository;
import com.bridgelabz.Fundoo.Service.LabelServiceInf;
import com.bridgelabz.Fundoo.Utility.JwtOperations;

@Service
public class LabelServiceImpl implements LabelServiceInf 
{
	@Autowired
	private LabelRepository labelrepo;
	@Autowired
	private LabelEntity labelentity;
	@Autowired
	private UserRepository userrepo;
	@Autowired
	private JwtOperations jwt;
	@Autowired
	private UserServiceImpl userimpl;
	@Autowired
	private NoteServiceImpl noteimpl;
	@Override
	public LabelEntity createLabel(LabelDto labeldto,String token) 
	{
		long id=jwt.parseJWT(token);
		UserEntity user=userimpl.getUserById(id);
		isLabelExists(labeldto.getLabelName());
		labelentity.setLabelName(labeldto.getLabelName());
		labelentity.setCreateDate(LocalDateTime.now());
		labelentity.setUpdateDate(LocalDateTime.now());
		user.getLabels().add(labelentity);
		userrepo.save(user);
		return labelentity;
	}
	private void isLabelExists(String labelName) {
		if(labelrepo.isLabelExists(labelName).isPresent())
			throw new CustomException("label already exists",HttpStatus.NOT_ACCEPTABLE,null);	
	}
	@Override
	public NoteEntity addLabelToNote(long noteid, String token) {
		long id=jwt.parseJWT(token);
		UserEntity user=userimpl.getUserById(id);
		//NoteEntity note=noteimpl.
		return null;
	}

}
