package com.bridgelabz.fundoo.service;

import java.util.List;

import com.bridgelabz.fundoo.model.LabelDTO;
import com.bridgelabz.fundoo.model.LabelModel;

public interface ILabelService {
	public LabelModel createLabel(String token, LabelDTO labelDto);
	public String updateLabel(String token, Long id,LabelDTO labelDto);
	public String deleteLabels(String token,Long id);
	public List<LabelModel> retrieveAllLabel(String token);
}