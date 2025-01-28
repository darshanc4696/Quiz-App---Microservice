package com.quiz.QuizApp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.quiz.QuizApp.model.Question;
import com.quiz.QuizApp.repo.QuestionRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class QuestionService {
	
	private QuestionRepository questionRepository;

	public List<Question> getAllQuestions() {
		return questionRepository.findAll();
	}
	

}
