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

import com.app.DTO.QuizResultDTO;
import com.app.entities.QuizResult;
import com.app.services.QuizResultService;
@CrossOrigin
@RestController
@RequestMapping("/quizResult")
public class QuizResultController {
@Autowired
private QuizResultService quizResultService;

@PostMapping("insert")
public ResponseEntity<String> addResult(@RequestBody QuizResultDTO quizResultDto){
	QuizResult q= quizResultService.addNewQuizResult(quizResultDto);
	return ResponseEntity.ok("result added " +q);
		
}
	
@PutMapping("update")
public ResponseEntity<String> updateResult(@RequestBody QuizResult quizResult){
	QuizResult q= quizResultService.updateQuizResult(quizResult);
	return ResponseEntity.ok("result updated  " +q);
		
}

@GetMapping("getAll")
public ResponseEntity<String> getAllResults(){
	List<QuizResult> q= quizResultService.getAll();
	return ResponseEntity.ok("all quizResult :"+ q);
}
	
@DeleteMapping("delete/{quizResId}")
public ResponseEntity<String> deleteResult(@PathVariable Long quizResId){
	quizResultService.deleteQuizResult(quizResId);
	return ResponseEntity.ok("quiz result deleted successfully ");
}
	
@GetMapping("getByQuizResultId/{quizResId}")
public QuizResult getById(@PathVariable Long quizResId) {
	return quizResultService.getByIdResult(quizResId);
}


	
	
	
}
