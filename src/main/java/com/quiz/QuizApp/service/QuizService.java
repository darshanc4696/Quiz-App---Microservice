package com.quiz.QuizApp.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quiz.QuizApp.model.Question;
import com.quiz.QuizApp.model.Quiz;
import com.quiz.QuizApp.repo.QuestionRepository;
import com.quiz.QuizApp.repo.QuizRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuestionRepository questionRepository;
    private final QuizRepository quizRepository;

    public ResponseEntity<String> createQuiz(String category, String title, int numQ) {
        Pageable pageable = PageRequest.of(0, numQ); // Acts as LIMIT
        List<Question> questions = questionRepository.findRandomQuestionsByCategory(category, numQ);

//        if (questions.isEmpty()) {
//            return new ResponseEntity<>("No questions found for the given category", HttpStatus.NOT_FOUND);
//        }

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestion(questions); // Ensure the method is `setQuestions`, not `setQuestion`

        quizRepository.save(quiz);
        return new ResponseEntity<>("Quiz created successfully", HttpStatus.CREATED);
    }
}
