package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.DTO.QuestionDTO;
import com.app.entities.Question;
import com.app.services.QuestionService;
@CrossOrigin

@RestController 
@RequestMapping("/question")
public class QuestionController {
@Autowired
private QuestionService questionService;

@PostMapping("/insert")
public ResponseEntity<String> addNewQuestions(@RequestBody QuestionDTO  questionDto ){
	Question q= questionService.addNewQuestions(questionDto);
	return ResponseEntity.ok("questions added Successfully : "+q);
}


@PutMapping("/update/{id}")
public ResponseEntity<String> updateQuestions(@RequestBody Question  question ){
	Question q= questionService.updateQuestions(question);
	return ResponseEntity.ok("questions added Successfully : "+q);
}

@DeleteMapping("/delete/{id}")
public ResponseEntity<String> deleteQuestions(@PathVariable Long queId){
	questionService.deleteQuestion(queId);
	return ResponseEntity.ok("deleted successfully ");
}


@GetMapping("getAllQuestions")
public ResponseEntity<List<Question>> getAllQuestions(){
	List<Question> q= questionService.getAllQuestions();
	return ResponseEntity.ok(q);
}


@GetMapping("getByQuestionId/{queId}")
public Question getById(@PathVariable Long queId) {
	return questionService.getById(queId);
}

}
