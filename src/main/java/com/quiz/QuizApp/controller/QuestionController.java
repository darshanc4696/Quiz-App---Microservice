package com.quiz.QuizApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

}
