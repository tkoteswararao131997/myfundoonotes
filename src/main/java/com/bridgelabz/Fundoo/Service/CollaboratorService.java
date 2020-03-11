package com.bridgelabz.Fundoo.Service;

import java.util.List;

import com.bridgelabz.Fundoo.Dto.CollaboratorDto;
import com.bridgelabz.Fundoo.Entity.CollaboratorEntity;

public interface CollaboratorService {
	CollaboratorEntity addColabToNote(CollaboratorDto colabDto, String token, long noteid);
	void deleteColabFromNote(CollaboratorDto colabdto, String token, long noteid);
	List<CollaboratorEntity> getAllColabs(String token, long noteid);

}
