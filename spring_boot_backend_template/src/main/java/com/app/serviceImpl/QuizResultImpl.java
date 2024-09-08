package com.app.serviceImpl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.DTO.QuizResultDTO;
import com.app.ResourceNotFoundException.ResourceNotFoundException;
import com.app.entities.Quiz;
import com.app.entities.QuizResult;
import com.app.repository.QuizRepository;
import com.app.repository.QuizResultRepository;
import com.app.services.QuizResultService;

@Service
@Transactional 
public class QuizResultImpl implements QuizResultService{

@Autowired
private QuizResultRepository quizResultRepository;

@Autowired
private QuizRepository quizRepository;

	
	
	
	@Override
	public List<QuizResult> getAll() {
		
		return quizResultRepository.findAll();
	}

	@Override
	public QuizResult updateQuizResult(QuizResult quizResult) {
		
		return quizResultRepository.save(quizResult);
	}

	@Override
	public void deleteQuizResult(Long quizResId) {
		quizResultRepository.deleteById(quizResId);
		
	}

	@Override
	public QuizResult getByIdResult(Long quizResId) {
		Optional<QuizResult> o= quizResultRepository.findById(quizResId);
		return o.orElseThrow(()-> new ResourceNotFoundException("id not found"));
	}

	 @Override
	    public QuizResult addNewQuizResult(QuizResultDTO quizResultDto) {
	     
	        QuizResult quizResult = new QuizResult();

	        quizResult.setUserId(quizResultDto.getUserId());
	        quizResult.setTotalObtainedMarks(quizResultDto.getTotalObtainedMarks());
	        quizResult.setAttemptDatetime(quizResultDto.getAttemptDatetime());

	        Quiz quiz = quizRepository.findById(quizResultDto.getQuizId())
	                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found"));
	        quizResult.setQuiz(quiz);

	        // Save the QuizResult entity
	        return quizResultRepository.save(quizResult);
	    }
	

}
