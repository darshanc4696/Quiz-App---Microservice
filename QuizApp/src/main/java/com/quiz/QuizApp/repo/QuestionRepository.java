package com.quiz.QuizApp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.quiz.QuizApp.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

    List<Question> findAllByCategory(String category);

    @Query(value = "SELECT * FROM question q Where q.category=:category", nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(String category);
}
