package com.bridgelabz.Fundoo.ServiceImpl;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

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
import com.bridgelabz.Fundoo.Repository.NoteRepository;
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
	@Autowired
	private NoteRepository noterepo;
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
	public LabelEntity addNoteToLabel(long noteid, String token, long labelid) {
		long userid=jwt.parseJWT(token);
		UserEntity user=userimpl.getUserById(userid);
		NoteEntity note=noteimpl.getNoteById(noteid, userid);
		LabelEntity label=getLabelById(labelid);
		note.getLabels().add(label);
		noterepo.save(note);
		return label;
	}
	
	@Override
	public LabelEntity getLabelById(long labelid)
	{
		return labelrepo.getLabelById(labelid).orElseThrow(() -> new CustomException("no label is present",HttpStatus.NOT_FOUND,null));
	}
	@Override
	public LabelEntity updateLabel(LabelDto labeldto, String token, long labelid) {
		long userid=jwt.parseJWT(token);
		UserEntity user=userimpl.getUserById(userid);
		LabelEntity label=getLabelById(labelid);
		label.setLabelName(labeldto.getLabelName());
		label.setUpdateDate(LocalDateTime.now());
		labelrepo.save(label);
		return getLabelById(label.getLabelId());
	}
	@Override
	public void deleteLabel(String token, long labelid) {
		long userid=jwt.parseJWT(token);
		UserEntity user=userimpl.getUserById(userid);
		LabelEntity label=getLabelById(labelid);
		labelrepo.setcheck();
		labelrepo.delete(label);
		
	}
	@Override
	public List<LabelEntity> getAllLabels(String token) {
		
		return labelrepo.getAllLabels(jwt.parseJWT(token)).orElseThrow(() -> new CustomException("no labels found",HttpStatus.NOT_FOUND,null));
	}
	public void deleteLabelFromNote(String token, long labelid, long noteid) {
		long userid=jwt.parseJWT(token);
		UserEntity user=userimpl.getUserById(userid);
		NoteEntity note=noteimpl.getNoteById(noteid, userid);
		LabelEntity label=labelrepo.getLabelById(labelid).orElseThrow(() -> new CustomException("no label is present",HttpStatus.NOT_FOUND,null));
		int id=labelrepo.islabelwithnote(label.getLabelId(),note.getNoteId()).orElseThrow(() -> new CustomException("label not present",HttpStatus.NOT_FOUND,null));
		System.out.println(id);
		note.getLabels().remove(label);
		noterepo.save(note);
	}
	

}
