package com.bridgelabz.Fundoo.ServiceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bridgelabz.Fundoo.Dto.NoteDto;
import com.bridgelabz.Fundoo.Dto.UpdateNoteDto;
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
		List<NoteEntity> notes=noterepo.getAllNotes(id).orElseThrow(() -> new CustomException("no notes in the list",HttpStatus.NOT_FOUND,null));
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
		return noterepo.getNoteById(noteid, userid).orElseThrow(() -> new CustomException("no notes in the list",HttpStatus.NOT_FOUND,null));
	}
	@Override
	public void deleteAllNotes(String token) 
	{
		long userid= jwt.parseJWT(token);
		userimpl.getUserById(userid);
		noterepo.getAllNotes(userid);
		noterepo.deleteAllNotes(userid);
	}
	@Override
	public NoteEntity updateNote(String token, long noteid, UpdateNoteDto updatenotedto) {
		long userid= jwt.parseJWT(token);
		userimpl.getUserById(userid);
		NoteEntity note=getNoteById(noteid, userid);
		BeanUtils.copyProperties(updatenotedto, note);
		noterepo.save(note);
		return note;
	}
	@Override
	public NoteEntity isPinNote(String token, long noteid) {
		long userid= jwt.parseJWT(token);
		userimpl.getUserById(userid);
		NoteEntity note=getNoteById(noteid, userid);
		if(note.isPinned()==false)
			note.setPinned(true);
		else
			note.setPinned(false);
		note.setArchieve(false);
		note.setTrashed(false);
		noterepo.save(note);
		return note;
	}
	@Override
	public NoteEntity isArchieveNote(String token, long noteid) {
		long userid= jwt.parseJWT(token);
		userimpl.getUserById(userid);
		NoteEntity note=getNoteById(noteid, userid);
		note.setPinned(false);
		if(note.isArchieve()==false)
			note.setArchieve(true);
		else
			note.setArchieve(false);
		note.setArchieve(false);
		note.setTrashed(false);
		noterepo.save(note);
		return note;
	}
	@Override
	public NoteEntity isTrashed(String token, long noteid) {
		long userid= jwt.parseJWT(token);
		userimpl.getUserById(userid);
		NoteEntity note=getNoteById(noteid, userid);
		note.setPinned(false);
		note.setArchieve(false);
		if(note.isTrashed()==false)
			note.setTrashed(true);
		else
			note.setTrashed(false);
		noterepo.save(note);
		return note;
	}
	@Override
	public NoteEntity remindMe(String token, long noteid,LocalDateTime reminderDate) {
		long userid= jwt.parseJWT(token);
		userimpl.getUserById(userid);
		NoteEntity note=getNoteById(noteid, userid);
		note.setReminde(reminderDate);
		noterepo.save(note);
		return note;
	}
	public List<NoteEntity> getAllPinNotes(String token) {
		long id=jwt.parseJWT(token);
		userentity=userimpl.getUserById(id);
		List<NoteEntity> notes=noterepo.getAllPinNotes(id).orElseThrow(() -> new CustomException("no pinned-notes in the list",HttpStatus.NOT_FOUND,null));
		return notes;
	}
	public List<NoteEntity> getAllArchieveNotes(String token) {
		long id=jwt.parseJWT(token);
		userentity=userimpl.getUserById(id);
		List<NoteEntity> notes=noterepo.getAllArchieveNotes(id).orElseThrow(() -> new CustomException("no archieve-notes in the list",HttpStatus.NOT_FOUND,null));
		return notes;
	}
	

}
