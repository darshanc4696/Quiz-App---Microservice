package com.quiz.QuizApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quiz.QuizApp.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

}
