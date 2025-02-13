package com.quiz.QuizApp.wrappers;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuizResponseWrapper {
	
	private int id;
	private String response;

}
