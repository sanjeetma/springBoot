package com.bridgelabz.fundoo.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.model.LabelDTO;
import com.bridgelabz.fundoo.model.LabelModel;
import com.bridgelabz.fundoo.model.Note;
import com.bridgelabz.fundoo.model.User;
import com.bridgelabz.fundoo.repository.LabelRepository;
import com.bridgelabz.fundoo.repository.UserDao;
import com.bridgelabz.fundoo.repository.UserNotesDao;
import com.bridgelabz.fundoo.util.JwtProvider;

@Service
public class LabelServiceImpl implements ILabelService{

@Autowired
private JwtProvider jwtUtility;

@Autowired
private UserDao userRepository;

//@Autowired
//private ModelMapper modelMapper;

@Autowired
private LabelRepository labelRepository;

@Autowired
private UserNotesDao noteRepositoy;

@Override
public LabelModel createLabel(String token, LabelDTO labelDto) {
	System.err.println("create label ");
	LabelModel label=new LabelModel(labelDto);
	Long verifiedId=jwtUtility.verifyToken(token);
	Optional<User> userModel=userRepository.findById(verifiedId);
	userModel.get().getLabel().add(label);
	labelRepository.save(label);
	return null;
}
	
	

@Override
public String updateLabel(String token, Long id, LabelDTO labelDto) {
	String message=null;
	Long verifiedId=jwtUtility.verifyToken(token);
	Optional<User> userModel=userRepository.findById(verifiedId);
	Optional<LabelModel> labelModel=labelRepository.findById(id);
	Optional<Note> noteModel=noteRepositoy.findById(id);
	if(userModel.isPresent()) {
		if(labelModel.isPresent()&& labelDto.getLabel()!=null) {
			//verify usermodel
			labelModel.get().setLabel(labelDto.getLabel());
			
			//userModel.get().getNote().addAll(noteModel);
			labelRepository.save(labelModel.get());
			message="Label is update";
		}
		else {
			//throw new CustomException(HttpStatus.NOT_ACCEPTABLE.value(), "Label is not exist");
		}
	}
	else {
		//throw new CustomException(HttpStatus.NOT_ACCEPTABLE.value(), "User is not exit");
	}
	return message;
}
@Override
public String deleteLabels(String token, Long id) {
	String message=null;
	Long verifiedId=jwtUtility.verifyToken(token);
	Optional<User> userModel=userRepository.findById(verifiedId);
	Optional<LabelModel> labelModel=labelRepository.findById(id);
	if (userModel.isPresent()) {
		if(labelModel.isPresent()) {
			labelRepository.delete(labelModel.get());;
             
             System.out.println("label deleted");
		}
		else {
			System.out.println("label is not exit");
			
		}
	}
	else {
		System.out.println("user is not exit");
		
	}
	return message; 
}

@Override
public List<LabelModel> retrieveAllLabel(String token) {
	Long verifiedUserId = jwtUtility.verifyToken(token);
	List<LabelModel> label=null;
	Optional<User> userModel = userRepository.findById(verifiedUserId);
	if (userModel.isPresent()) 
	label=userModel.get().getLabel();
	
	return label;
}





}



