package com.bridgelabz.Fundoo.ServiceImpl;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

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
@CrossOrigin("*")
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
			throw new CustomException("label already exists",HttpStatus.OK,null,"false");	
	}
	@Override
	public LabelEntity addNoteToLabel(Long noteid, String token, Long labelid) {
		long userid=jwt.parseJWT(token);
		UserEntity user=userimpl.getUserById(userid);
		NoteEntity note=noteimpl.getNoteById(noteid, userid);
		LabelEntity label=getLabelById(labelid);
		boolean present=labelrepo.islabelwithnote(labelid, noteid).isPresent();
		System.out.println(present);
		if(present==true)
			throw new CustomException("label already exists",HttpStatus.OK,null,"false");	
		note.getLabels().add(label);
		noterepo.save(note);
		return label;
	}
	
	@Override
	public LabelEntity getLabelById(Long labelid)
	{
		System.out.println();
		return labelrepo.getLabelById(labelid).orElseThrow(() -> new CustomException("no label is present",HttpStatus.OK,null,"false"));
	}
	@Override
	public LabelEntity updateLabel(LabelDto labeldto, String token, Long labelid) {
		long userid=jwt.parseJWT(token);
		UserEntity user=userimpl.getUserById(userid);
		LabelEntity label=getLabelById(labelid);
		label.setLabelName(labeldto.getLabelName());
		label.setUpdateDate(LocalDateTime.now());
		labelrepo.save(label);
		return getLabelById(label.getLabelId());
	}
	@Override
	public void deleteLabel(String token, Long labelid) {
		Long userid=jwt.parseJWT(token);
		UserEntity user=userimpl.getUserById(userid);
		LabelEntity label=getLabelById(labelid);
		labelrepo.setcheck();
		labelrepo.deleteLabelFromNote(labelid);
		labelrepo.deleteLabel(labelid);
		
//		user.getNotes().remove(label);
//		userrepo.save(user);
		
	}
	@Override
	public List<LabelEntity> getAllLabels(String token) {
		
		return labelrepo.getAllLabels(jwt.parseJWT(token)).orElseThrow(() -> new CustomException("no labels found",HttpStatus.OK,null,"false"));
	}
	public void deleteLabelFromNote(String token, Long labelid, Long noteid) {
		long userid=jwt.parseJWT(token);
		UserEntity user=userimpl.getUserById(userid);
		NoteEntity note=noteimpl.getNoteById(noteid, userid);
		LabelEntity label=labelrepo.getLabelById(labelid).orElseThrow(() -> new CustomException("no label is present",HttpStatus.OK,null,"false"));
		int id=labelrepo.islabelwithnote(label.getLabelId(),note.getNoteId()).orElseThrow(() -> new CustomException("label not present",HttpStatus.OK,null,"false"));
		System.out.println(id);
		note.getLabels().remove(label);
		noterepo.save(note);
	}
	public List<NoteEntity> getnotesfromlabel(String token, Long labelid) {
		long userid=jwt.parseJWT(token);
		UserEntity user=userimpl.getUserById(userid);
		LabelEntity label=labelrepo.getLabelById(labelid).orElseThrow(() -> new CustomException("no label is present",HttpStatus.OK,null,"false"));
		List<NoteEntity> notelist=label.getNotes();
//		List<NoteEntity> notelist=labelrepo.getnotesfromlabel(labelid);
		return notelist;
	}
	public void isnoteinlabel(Long noteId,Long labelId)
	{
		labelrepo.isnoteinlabel(noteId,labelId);
	}
	public List<LabelEntity> createLabelAddNote(String token, LabelDto labeldto, Long noteid) {
		long userid=jwt.parseJWT(token);
		UserEntity user=userimpl.getUserById(userid);
		NoteEntity note=noteimpl.getNoteById(noteid, userid);
		isLabelExists(labeldto.getLabelName());
		labelentity.setLabelName(labeldto.getLabelName());
		labelentity.setCreateDate(LocalDateTime.now());
		labelentity.setUpdateDate(LocalDateTime.now());
		user.getLabels().add(labelentity);
		userrepo.save(user);
		List<LabelEntity> labels=user.getLabels();
		for(LabelEntity label : labels)
		{
			if(labeldto.getLabelName().equals(label.getLabelName()))
					addNoteToLabel(noteid, token, label.getLabelId());
		}
		return note.getLabels();
	}
	

}
