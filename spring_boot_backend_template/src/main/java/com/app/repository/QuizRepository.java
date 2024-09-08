package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entities.Quiz;
@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long>{

}
