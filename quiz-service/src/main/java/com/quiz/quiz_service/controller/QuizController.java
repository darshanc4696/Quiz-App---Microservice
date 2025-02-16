package com.quiz.quiz_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.quiz_service.model.QuizDto;
import com.quiz.quiz_service.service.QuizService;
import com.quiz.quiz_service.wrappers.QuestionWrapper;
import com.quiz.quiz_service.wrappers.QuizResponseWrapper;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/quiz")
@AllArgsConstructor
public class QuizController {
	
	private QuizService quizService;
	
	@PostMapping("/create")
	public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto)
	{
		return quizService.createQuiz(quizDto.getCategory(), quizDto.getNumQ(), quizDto.getTitle());
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
