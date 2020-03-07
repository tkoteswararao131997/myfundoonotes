package com.bridgelabz.Fundoo.Service;

import com.bridgelabz.Fundoo.Dto.LabelDto;
import com.bridgelabz.Fundoo.Entity.LabelEntity;
import com.bridgelabz.Fundoo.Entity.NoteEntity;

public interface LabelServiceInf {
	LabelEntity createLabel(LabelDto labeldto, String token);

	NoteEntity addLabelToNote(long noteid, String token);
}
