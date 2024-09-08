package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entities.QuizResult;
@Repository
public interface QuizResultRepository extends JpaRepository<QuizResult, Long>{

}
