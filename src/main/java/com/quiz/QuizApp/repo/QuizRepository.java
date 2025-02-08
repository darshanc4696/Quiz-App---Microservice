package com.quiz.QuizApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quiz.QuizApp.model.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer>{

}
