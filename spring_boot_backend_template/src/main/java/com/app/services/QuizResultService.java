package com.app.services;

import java.util.List;

import com.app.DTO.QuizResultDTO;
import com.app.entities.QuizResult;

public interface QuizResultService {
QuizResult addNewQuizResult(QuizResultDTO quizResultDto);
List<QuizResult> getAll();
QuizResult updateQuizResult(QuizResult quizResult);
void deleteQuizResult(Long quizResId);
QuizResult getByIdResult(Long quizResId);

}
