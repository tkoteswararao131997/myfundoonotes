package com.bridgelabz.Fundoo.ServiceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bridgelabz.Fundoo.Dto.NoteDto;
import com.bridgelabz.Fundoo.Entity.NoteEntity;
import com.bridgelabz.Fundoo.Entity.UserEntity;
import com.bridgelabz.Fundoo.Exception.CustomException;
import com.bridgelabz.Fundoo.Repository.NoteRepository;
import com.bridgelabz.Fundoo.Repository.UserRepository;
import com.bridgelabz.Fundoo.Service.NoteServiceInf;
import com.bridgelabz.Fundoo.Utility.JwtOperations;
@Service
public class NoteServiceImpl implements NoteServiceInf {
	@Autowired
	private NoteRepository noterepo;
	@Autowired
	private UserServiceImpl userimpl;
	@Autowired
	private JwtOperations jwt;
	@Autowired
	private UserEntity userentity;
	@Autowired
	private NoteEntity noteentity;
	@Autowired
	private UserRepository userrepo;
	@Override
	public NoteEntity addNote(NoteDto notedto, String token) {
		long id=jwt.parseJWT(token);
		System.out.println(id+"--------------");
		if(noterepo.isNoteExists(notedto.getTitle(),id).isPresent())
			throw new CustomException("note already exists", HttpStatus.NOT_ACCEPTABLE,null);
		userentity=userimpl.getUserById(id);
		BeanUtils.copyProperties(notedto, noteentity);
		noteentity.setArchieve(false);
		noteentity.setCreateDate(LocalDateTime.now());
		noteentity.setUpdateDate(LocalDateTime.now());
		noteentity.setReminde(LocalDateTime.now());
		noteentity.setPinned(false);
		noteentity.setTrashed(false);
		userentity.getNotes().add(noteentity);
		userrepo.save(userentity);
		return noteentity;
	}
	@Override
	public List<NoteEntity> getAllNotes(String token) {
		long id=jwt.parseJWT(token);
		userentity=userimpl.getUserById(id);
		List<NoteEntity> notes=noterepo.getAllNotes(id).orElseThrow(() -> new CustomException("no notes in the list",HttpStatus.OK,null));
		return notes;
	}
	@Override
	public void deleteNoteById(String token, long noteid) {
		long userid= jwt.parseJWT(token);
		userimpl.getUserById(userid);
		getNoteById(noteid, userid);
		noterepo.deleteNoteById(noteid,userid);
	}
	
	public NoteEntity getNoteById(long noteid,long userid)
	{
		return noterepo.getNoteById(noteid, userid).orElseThrow(() -> new CustomException("no notes in the list",HttpStatus.OK,null));
	}
	@Override
	public void deleteAllNotes(String token) {
		long userid= jwt.parseJWT(token);
		userimpl.getUserById(userid);
		return noterepo.deleteAllNotes(long userid)
	}

}
