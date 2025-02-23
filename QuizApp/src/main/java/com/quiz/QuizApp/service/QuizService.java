package com.quiz.QuizApp.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quiz.QuizApp.model.Question;
import com.quiz.QuizApp.model.Quiz;
import com.quiz.QuizApp.repo.QuestionRepository;
import com.quiz.QuizApp.repo.QuizRepository;
import com.quiz.QuizApp.wrappers.QuestionWrapper;
import com.quiz.QuizApp.wrappers.QuizResponseWrapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuestionRepository questionRepository;
    private final QuizRepository quizRepository;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

    	List<Question> questions = questionRepository.findAllByCategory(category);
        Collections.shuffle(questions);  // Randomize questions
        List<Question> selectedQuestions = questions.stream().limit(numQ).toList();

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestion(selectedQuestions);
        quizRepository.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);

    }

	public ResponseEntity<List<QuestionWrapper>> getQuestionsBasedOnId(int id) {
		Optional<Quiz> quiz = quizRepository.findById(id);
		List<Question> questions = quiz.get().getQuestion();
		List<QuestionWrapper> questionWrappers = new ArrayList<QuestionWrapper>();
		
		for(Question q : questions)
		{
			questionWrappers.add(new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4()));
		}
		
		return new ResponseEntity<>(questionWrappers, HttpStatus.OK);
	}

	public int calculate(int id, List<QuizResponseWrapper> response) {
		Optional<Quiz> quiz = quizRepository.findById(id);
		List<Question> questions = quiz.get().getQuestion();
		
		int correct_answer = 0;
		int i = 0;
		
		for(QuizResponseWrapper q : response)
		{
			if(q.getResponse().equals(questions.get(i).getRightAnswer()))
			{
				correct_answer += 1;
			}
			i++;
		}
		
		return correct_answer;
	}

}
