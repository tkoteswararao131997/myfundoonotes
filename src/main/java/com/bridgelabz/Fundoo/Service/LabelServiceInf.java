package com.bridgelabz.Fundoo.Service;

import java.util.List;

import com.bridgelabz.Fundoo.Dto.LabelDto;
import com.bridgelabz.Fundoo.Entity.LabelEntity;
import com.bridgelabz.Fundoo.Entity.NoteEntity;

public interface LabelServiceInf {
	LabelEntity createLabel(LabelDto labeldto, String token);

	LabelEntity addNoteToLabel(Long noteid, String token,Long labelid);

	LabelEntity updateLabel(LabelDto labeldto, String token, Long labelid);

	List<LabelEntity> getAllLabels(String token);

	void deleteLabel(String token, Long labelid);

	LabelEntity getLabelById(Long labelid);
}
