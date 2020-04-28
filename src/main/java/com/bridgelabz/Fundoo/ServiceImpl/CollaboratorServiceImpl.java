package com.bridgelabz.Fundoo.ServiceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bridgelabz.Fundoo.Dto.CollaboratorDto;
import com.bridgelabz.Fundoo.Entity.NoteEntity;
import com.bridgelabz.Fundoo.Entity.UserEntity;
import com.bridgelabz.Fundoo.Exception.CustomException;
import com.bridgelabz.Fundoo.Repository.CollaboratorRepository;
import com.bridgelabz.Fundoo.Repository.NoteRepository;
import com.bridgelabz.Fundoo.Service.CollaboratorService;
import com.bridgelabz.Fundoo.Utility.JwtOperations;
@Service
public class CollaboratorServiceImpl implements CollaboratorService {
	
	@Autowired
	private JwtOperations jwt;
	
	@Autowired
	private UserServiceImpl userimpl;
	
	@Autowired
	private NoteServiceImpl noteimpl;
	
	@Autowired
	private NoteRepository noterepo;
	@Autowired
	private CollaboratorRepository collabrepo;
	@Override
	public NoteEntity addColabToNote(String colabEmail, String token, long noteid) {
		long userid=jwt.parseJWT(token);
		UserEntity user=userimpl.getUserById(userid);
		if((user.getEmail().equals(colabEmail)))
			throw new CustomException("u r the owner",HttpStatus.NOT_ACCEPTABLE,null,"false");
		NoteEntity note=noteimpl.getNoteById(noteid, userid);
		UserEntity colabuser=userimpl.getUserByEmail(colabEmail);
		note.getCollaborators().add(colabuser);
		noterepo.save(note);
		return note;
	}

	@Override
	public void deleteColabFromNote(String colabEmail, String token, long noteid) {
		long userid=jwt.parseJWT(token);
		UserEntity user=userimpl.getUserById(userid);
		System.out.println(colabEmail);
		//UserEntity colab=userimpl.getUserById(colabEmail);
		NoteEntity note=noteimpl.getNoteById(noteid, userid);
		UserEntity colabuser=userimpl.getUserByEmail(colabEmail);
		if(collabrepo.isColabInNote(colabuser.getUserid(),note.getNoteId()).isPresent())
		{
			note.getCollaborators().remove(colabuser);
			noterepo.save(note);
		}
		else
			throw new CustomException("collaborator not present",HttpStatus.NOT_FOUND,null,"false");
			
	}

	@Override
	public List<UserEntity> getAllColabs(String token, long noteid) {
		long userid=jwt.parseJWT(token);
		UserEntity user=userimpl.getUserById(userid);
		NoteEntity note=noteimpl.getNoteById(noteid, userid);
		List<UserEntity> colabusers=note.getCollaborators();
		return colabusers;
	}

}
