package com.quiz.QuizApp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quiz.QuizApp.model.Question;
import com.quiz.QuizApp.repo.QuestionRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class QuestionService {
	
	private QuestionRepository questionRepository;

	public ResponseEntity<List<Question>> getAllQuestions() {
		try {
			return new ResponseEntity<List<Question>>(questionRepository.findAll(), HttpStatusCode.valueOf(201));
//			return ResponseEntity.ok(questionRepository.findAll());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.ok(new ArrayList<>());
	}

	public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
		
		try {
			return ResponseEntity.ok(questionRepository.findAllByCategory(category));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.ok(new ArrayList<>());
	}

	public ResponseEntity<String> addQuestion(Question question) {
		
		try {
			questionRepository.save(question);
			return ResponseEntity.ok("success");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return (ResponseEntity<String>) ResponseEntity.internalServerError();
		
	}
	

}
