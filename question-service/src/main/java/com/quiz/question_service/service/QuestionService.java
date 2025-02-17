package com.quiz.question_service.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quiz.question_service.model.Question;
import com.quiz.question_service.repo.QuestionRepository;
import com.quiz.question_service.wrappers.QuestionWrapper;
import com.quiz.question_service.wrappers.QuizResponseWrapper;

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

		return new ResponseEntity<String>("success", HttpStatus.OK);

	}

	public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, Integer numQ) {
		List<Integer> questions = questionRepository.findRandomQuestionsByCategory(categoryName);
		Collections.shuffle(questions);
		List<Integer> selectedQuestions = questions.subList(0, Math.min(numQ, questions.size()));
		return new ResponseEntity<>(selectedQuestions, HttpStatus.OK);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds) {
		List<QuestionWrapper> wrappers = new ArrayList<>();
		List<Question> questions = new ArrayList<>();

		for(Integer id : questionIds){
			questions.add(questionRepository.findById(id).get());
		}

		for(Question question : questions){
			QuestionWrapper wrapper = new QuestionWrapper();
			wrapper.setId(question.getId());
			wrapper.setQuestionTitle(question.getQuestionTitle());
			wrapper.setOption1(question.getOption1());
			wrapper.setOption2(question.getOption2());
			wrapper.setOption3(question.getOption3());
			wrapper.setOption4(question.getOption4());
			wrappers.add(wrapper);
		}

		return new ResponseEntity<>(wrappers, HttpStatus.OK);
	}

	public ResponseEntity<Integer> getScore(List<QuizResponseWrapper> responses) {


		int right = 0;

		for(QuizResponseWrapper response : responses){
			Question question = questionRepository.findById(response.getId()).get();
			if(response.getResponse().equals(question.getRightAnswer()))
				right++;
		}
		return new ResponseEntity<>(right, HttpStatus.OK);
	}


}
