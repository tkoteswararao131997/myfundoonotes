package com.bridgelabz.Fundoo.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bridgelabz.Fundoo.Entity.NoteEntity;
public interface CollaboratorRepository extends JpaRepository<NoteEntity,String>{

	@Query(value = "select * from users where email=?1",nativeQuery = true)
	Optional<NoteEntity> isColabExists(String colabemail);
//	@Query(value = "select * from users where colaborators_note_id=?1",nativeQuery = true)
//	Optional<List<NoteEntity>> getall(long l);
	
	@Query(value="select collaborator_notes_note_id from notes_collaborator_users where collaborator_notes_note_id=?2 and collaborator_users_userid=?1",nativeQuery = true)
	Optional<Integer> isColabInNote(Long userid, long noteId);
}
