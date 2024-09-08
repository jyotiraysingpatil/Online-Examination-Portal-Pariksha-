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

import com.app.DTO.QuizDTO;
import com.app.entities.Quiz;
import com.app.services.QuizService;
@CrossOrigin
@RestController
@RequestMapping("/quiz")
public class QuizController {
@Autowired
private QuizService quizService;

@PostMapping("insert")
public ResponseEntity<String> addNewQuiz(@RequestBody QuizDTO quizDto){
	Quiz q=quizService.addNew(quizDto);
	 return ResponseEntity.ok("quiz added :" +q);
	
}

@PutMapping("update/{id}")
public ResponseEntity<String> updateQuizDetails(@RequestBody Quiz quiz){
	Quiz q= quizService.updateQuiz(quiz);
	 return ResponseEntity.ok("quiz added :" +q);
	
}

@GetMapping("getAll")
public ResponseEntity<List<Quiz>> getAll(){
	List<Quiz> q= quizService.getAll();
	return ResponseEntity.ok( q);
	
}

@DeleteMapping("delete/{quizId}")
public ResponseEntity<String> deleteStudentDetails(@PathVariable Long quizId) {
    quizService.deleteQuiz(quizId);
    return ResponseEntity.ok("Quiz deleted successfully");
}

@GetMapping("getQuizById/{quizId}")
public Quiz getQuizById(Long quizId){
	return quizService.getById(quizId);
}




}
