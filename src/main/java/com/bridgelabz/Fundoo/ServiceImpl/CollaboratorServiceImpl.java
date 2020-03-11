//package com.bridgelabz.Fundoo.ServiceImpl;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//
//import com.bridgelabz.Fundoo.Dto.CollaboratorDto;
//import com.bridgelabz.Fundoo.Entity.CollaboratorEntity;
//import com.bridgelabz.Fundoo.Entity.NoteEntity;
//import com.bridgelabz.Fundoo.Entity.UserEntity;
//import com.bridgelabz.Fundoo.Exception.CustomException;
//import com.bridgelabz.Fundoo.Repository.CollaboratorRepository;
//import com.bridgelabz.Fundoo.Repository.NoteRepository;
//import com.bridgelabz.Fundoo.Service.CollaboratorService;
//import com.bridgelabz.Fundoo.Utility.JwtOperations;
//@Service
//public class CollaboratorServiceImpl implements CollaboratorService {
//
//	
//	@Autowired
//	private CollaboratorRepository colabrepo;
//	@Autowired
//	private JwtOperations jwt;
//	@Autowired
//	private UserServiceImpl userimpl;
//	@Autowired
//	private NoteServiceImpl noteimpl;
//	@Autowired
//	private CollaboratorEntity colab;
//	@Autowired
//	private NoteRepository noterepo;
//	@Override
//	public CollaboratorEntity addColabToNote(CollaboratorDto colabDto, String token, long noteid) {
//		long userid=jwt.parseJWT(token);
//		UserEntity user=userimpl.getUserById(userid);
//		NoteEntity note=noteimpl.getNoteById(noteid, userid);
//		if(colabrepo.isColabExists(colabDto.getColabEmail()).isPresent()==true)
//			throw new CustomException("collaborator already exists",HttpStatus.BAD_REQUEST,null);
//		BeanUtils.copyProperties(colabDto,colab);
//		colab.setAddedDate(LocalDateTime.now());
//		note.getColaborators().add(colab);
//		noterepo.save(note);
//		return colab;
//	}
//	@Override
//	public void deleteColabFromNote(CollaboratorDto colabdto, String token, long noteid) {
//		long userid=jwt.parseJWT(token);
//		UserEntity user=userimpl.getUserById(userid);
//		NoteEntity note=noteimpl.getNoteById(noteid, userid);
//		isColabExists(colabdto.getColabEmail());
//		note.getColaborators().remove(colabdto);
//	}
//	
//	public CollaboratorEntity isColabExists(String colabemail)
//	{
//		return colabrepo.isColabExists(colabemail).orElseThrow(() -> new CustomException("no colaborator exists",HttpStatus.NOT_FOUND,null));
//	}
//	@Override
//	public List<CollaboratorEntity> getAllColabs(String token, long noteid) {
//		long userid=jwt.parseJWT(token);
//		UserEntity user=userimpl.getUserById(userid);
//		NoteEntity note=noteimpl.getNoteById(noteid, userid);
//		return colabrepo.getall(note.getNoteId()).orElseThrow(() -> new CustomException("no collaborators found",HttpStatus.NOT_FOUND,null));
//	}
//
//}
