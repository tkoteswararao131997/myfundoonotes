package com.bridgelabz.Fundoo.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bridgelabz.Fundoo.Entity.NoteEntity;
public interface CollaboratorRepository extends JpaRepository<NoteEntity,String>{

	@Query(value = "select * from collaborators where colab_email=?1",nativeQuery = true)
	Optional<NoteEntity> isColabExists(String colabemail);
	@Query(value = "select * from collaborators where colaborators_note_id=?1",nativeQuery = true)
	Optional<List<NoteEntity>> getall(long l);
}
