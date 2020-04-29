package com.bridgelabz.Fundoo.Service;

import java.util.List;

import com.bridgelabz.Fundoo.Dto.CollaboratorDto;
import com.bridgelabz.Fundoo.Entity.NoteEntity;
import com.bridgelabz.Fundoo.Entity.UserEntity;
public interface CollaboratorService {
	//NoteEntity addColabToNote(CollaboratorDto colabDto, String token, long noteid);
	//void deleteColabFromNote(String colabEmail, String token, long noteid);
	List<UserEntity> getAllColabs(String token, long noteid);
	UserEntity addColabToNote(String colabEmail, String token, long noteid);
	//void deleteColabFromNote(Long colabId, String token, long noteid);
	//void deleteColabFromNote(long colabId, String token, long noteid);
	void deleteColabFromNote(String colabEmail, String token, long noteid);

}
