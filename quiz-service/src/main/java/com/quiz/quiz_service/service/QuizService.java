package com.quiz.quiz_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quiz.quiz_service.feign.QuizInterface;
import com.quiz.quiz_service.model.Quiz;
import com.quiz.quiz_service.repo.QuizRepository;
import com.quiz.quiz_service.wrappers.QuestionWrapper;
import com.quiz.quiz_service.wrappers.QuizResponseWrapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;
    
    @Autowired
    private QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

    	List<Integer> questionIds = quizInterface.getQuestionsForQuiz(category, numQ).getBody();

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questionIds);
        quizRepository.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);

    }

	public ResponseEntity<List<QuestionWrapper>> getQuestionsBasedOnId(int id) {
		Quiz quiz = quizRepository.findById(id).get();
		ResponseEntity<List<QuestionWrapper>> questions = quizInterface.getQuestionsFromId(quiz.getQuestionIds());
		
		return questions;
	}

	public int calculate(int id, List<QuizResponseWrapper> response) {
		int correct_answer = quizInterface.getScore(response).getBody();
		
		return correct_answer;
	}

}
