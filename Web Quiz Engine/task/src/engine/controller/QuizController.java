package engine.controller;


import engine.model.Answer;
import engine.model.Quiz;
import engine.service.AnswerService;
import engine.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @Autowired
    private AnswerService answerService;


    @GetMapping
    public Quiz getQuiz() {
        return quizService.createQuiz();
    }


    @PostMapping()
    public Answer checkAnswer(@RequestParam(name = "answer") int variant) {
        return answerService.createAnswer(variant);
    }


}
