package com.app.services;

import java.util.List;

import com.app.DTO.QuestionDTO;
import com.app.entities.Question;

public interface QuestionService {
Question addNewQuestions(QuestionDTO questionDto);
Question updateQuestions(Question question);
List<Question> getAllQuestions();
void deleteQuestion(Long quesId);
Question getById(Long quesId);
}
