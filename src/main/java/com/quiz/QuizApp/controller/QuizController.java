package com.quiz.QuizApp.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.QuizApp.service.QuizService;
import com.quiz.QuizApp.wrappers.QuestionWrapper;
import com.quiz.QuizApp.wrappers.QuizResponseWrapper;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/quiz")
@AllArgsConstructor
public class QuizController {
	
	private QuizService quizService;
	
	@GetMapping("/create")
	public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam String title, @RequestParam int numQ)
	{
		return quizService.createQuiz(category, numQ, title);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<List<QuestionWrapper>> getQuestionsOnId(@PathVariable int id)
	{
		return quizService.getQuestionsBasedOnId(id);
	}
	
	@GetMapping("/submit/{id}")
	public ResponseEntity<Integer> calculateTheScore(@PathVariable int id, @RequestBody List<QuizResponseWrapper> response)
	{
		int correct_answer = quizService.calculate(id, response);
		
		return new ResponseEntity<Integer>(correct_answer, HttpStatus.OK);
	}

}
