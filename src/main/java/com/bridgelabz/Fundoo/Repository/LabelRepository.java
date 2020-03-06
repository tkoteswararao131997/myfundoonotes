//package com.bridgelabz.Fundoo.Repository;
//
//import java.util.Optional;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import com.bridgelabz.Fundoo.Entity.LabelEntity;
//@Repository
//public interface LabelRepository extends JpaRepository<LabelEntity, String> {
//	@Query(value = "select label_name from labels where label_name=?1",nativeQuery = true)
//	Optional<String> isLabelExists(String label_name);
//
//	
//}
