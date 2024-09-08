package com.app.serviceImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.DTO.QuizDTO;
import com.app.ResourceNotFoundException.ResourceNotFoundException;
import com.app.entities.Categories;
import com.app.entities.Question;
import com.app.entities.Quiz;
import com.app.entities.QuizResult;
import com.app.repository.CategoryRepository;
import com.app.repository.QuestionRepository;
import com.app.repository.QuizRepository;
import com.app.repository.QuizResultRepository;
import com.app.services.QuizService;
@Service
@Transactional 
public class QuizImpl implements QuizService {

	@Autowired
	private QuizRepository quizRepository;
	@Autowired 
	private QuestionRepository questionRepository;
	@Autowired
	private QuizResultRepository quizResultRepository;

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public List<Quiz> getAll() {
	
		return quizRepository.findAll();
	}

	@Override
	public Quiz updateQuiz(Quiz quiz) {
		
		return quizRepository.save(quiz);
	}

	@Override
	public void deleteQuiz(Long quizId) {
		quizRepository.deleteById(quizId);

	}

	@Override
	public Quiz getById(Long quizId) {
	Optional<Quiz> o=quizRepository.findById(quizId);
	return o.orElseThrow(()-> new ResourceNotFoundException("id not found"));
	}

	@Override
	public Quiz addNew(QuizDTO quizDto) {
	    // Create a new Quiz entity
	    Quiz quiz = new Quiz();

	  
	    quiz.setTitle(quizDto.getTitle());
	    quiz.setDescription(quizDto.getDescription());
	    quiz.setActive(quizDto.isActive());
	    quiz.setNumOfQuestions(quizDto.getNumOfQuestions());
	    quiz.setMaxMarks(quizDto.getMaxMarks());

	    Categories category = categoryRepository.findById(quizDto.getCategoryId())
	            .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
	    quiz.setCategory(category);

	   
	    Set<Question> questions = quizDto.getQuestionIds().stream()
	            .map(questionId -> questionRepository.findById(questionId)
	                    .orElseThrow(() -> new ResourceNotFoundException("Question not found")))
	            .collect(Collectors.toSet());
	    quiz.setQuestions(questions);

	    
	    List<QuizResult> quizResults = quizDto.getQuizResultIds().stream()
	            .map(quizResultId -> quizResultRepository.findById(quizResultId)
	                    .orElseThrow(() -> new ResourceNotFoundException("Quiz Result not found")))
	            .collect(Collectors.toList());
	    quiz.setQuizResults(quizResults);
	    return quizRepository.save(quiz);
	}
}	
	


