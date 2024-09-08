package com.app.services;

import java.util.List;

import com.app.DTO.QuizDTO;
import com.app.entities.Quiz;

public interface QuizService {
Quiz addNew (QuizDTO quizDto);
List<Quiz> getAll();
Quiz updateQuiz(Quiz quiz);
void deleteQuiz(Long quizId);
Quiz getById(Long quizId);

}
