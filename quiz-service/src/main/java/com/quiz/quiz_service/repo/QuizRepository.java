package com.quiz.quiz_service.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quiz.quiz_service.model.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer>{

}
