package com.quiz.question_service.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.quiz.question_service.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

    List<Question> findAllByCategory(String category);

    @Query(value = "SELECT q.id FROM question q Where q.category=:category", nativeQuery = true)
    List<Integer> findRandomQuestionsByCategory(String category);
}
