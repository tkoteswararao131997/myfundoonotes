package com.bridgelabz.Fundoo.Service;

import java.util.List;

import com.bridgelabz.Fundoo.Dto.LabelDto;
import com.bridgelabz.Fundoo.Entity.LabelEntity;
import com.bridgelabz.Fundoo.Entity.NoteEntity;

public interface LabelServiceInf {
	LabelEntity createLabel(LabelDto labeldto, String token);

	LabelEntity addNoteToLabel(long noteid, String token,long labelid);

	LabelEntity getLabelById(long labelid);

	LabelEntity updateLabel(LabelDto labeldto, String token, long labelid);

	void deleteLabel(String token, long labelid);

	List<LabelEntity> getAllLabels(String token);
}
