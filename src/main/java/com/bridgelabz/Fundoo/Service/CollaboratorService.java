package com.bridgelabz.Fundoo.Service;

import java.util.List;

import com.bridgelabz.Fundoo.Dto.CollaboratorDto;
import com.bridgelabz.Fundoo.Entity.NoteEntity;
import com.bridgelabz.Fundoo.Entity.UserEntity;
public interface CollaboratorService {
	NoteEntity addColabToNote(CollaboratorDto colabDto, String token, long noteid);
	void deleteColabFromNote(CollaboratorDto colabdto, String token, long noteid);
	List<UserEntity> getAllColabs(String token, long noteid);

}
