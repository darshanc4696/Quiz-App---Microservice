package com.quiz.quiz_service.wrappers;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuizResponseWrapper {
	
	private int id;
	private String response;

}
