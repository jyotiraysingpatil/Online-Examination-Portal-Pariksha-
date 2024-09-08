package com.app.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.DTO.QuestionDTO;
import com.app.ResourceNotFoundException.ResourceNotFoundException;
import com.app.entities.Question;
import com.app.entities.Quiz;
import com.app.repository.QuestionRepository;
import com.app.repository.QuizRepository;
import com.app.services.QuestionService;
@Service
@Transactional
public class QuestionImpl implements QuestionService{
@Autowired 
private QuestionRepository questionRespository;

@Autowired
private QuizRepository quizRepository;


@Override
public Question updateQuestions(Question question) {
	
	return questionRespository.save(question);
}

@Override
public List<Question> getAllQuestions() {
	
	return questionRespository.findAll();
}

@Override
public void deleteQuestion(Long quesId) {
questionRespository.deleteById(quesId);
}

@Override
public Question getById(Long quesId) {
	Optional<Question>o=questionRespository.findById(quesId);
	return o.orElseThrow(()-> new ResourceNotFoundException("id not found"));
}

@Override
public Question addNewQuestions(QuestionDTO questionDto) {
    // Retrieve the Quiz entity using the provided quizId
    Quiz quiz = quizRepository.findById(questionDto.getQuesId())
            .orElseThrow(() -> new ResourceNotFoundException("Quiz not found with id: " + questionDto.getQuesId()));

    // Create a new Question entity and set its fields
    Question question = new Question();
    question.setContent(questionDto.getContent());
    question.setImage(questionDto.getImage());
    question.setOption1(questionDto.getOption1());
    question.setOption2(questionDto.getOption2());
    question.setOption3(questionDto.getOption3());
    question.setOption4(questionDto.getOption4());
    question.setAnswer(questionDto.getAnswer());
    question.setQuiz(quiz);  // Correctly set the Quiz entity

    // Save and return the Question entity
    return questionRespository.save(question);
}




}
