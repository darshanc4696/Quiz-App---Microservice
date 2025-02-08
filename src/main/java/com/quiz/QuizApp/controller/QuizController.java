package com.quiz.QuizApp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.QuizApp.service.QuizService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/quiz")
@AllArgsConstructor
public class QuizController {
	
	private QuizService quizService;
	
	@GetMapping("/create")
	public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam String title, @RequestParam int numQ)
	{
		return quizService.createQuiz(category, title, numQ);
	}

}
