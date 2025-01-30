package com.quiz.QuizApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.QuizApp.model.Question;
import com.quiz.QuizApp.service.QuestionService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/question")
public class QuestionController {
	
	@Autowired
	private QuestionService questionService;
	
	@GetMapping("/getQuestions")
	public List<Question> getAllQuestions()
	{
		return questionService.getAllQuestions();
	}
	
	@GetMapping("/category/{category}")
	public List<Question> getQuestionsByCategory(@PathVariable String category)
	{
		return questionService.getQuestionsByCategory(category);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Question> addQuestion(@RequestBody Question question)
	{
		return ResponseEntity.ok(questionService.addQuestion(question));
	}

}
