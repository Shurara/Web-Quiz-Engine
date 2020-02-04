package controller;


import model.Answer;
import model.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.AnswerService;
import service.QuizService;

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


    @PostMapping("/{variant}")
    public Answer checkAnswer(@PathVariable int variant){
        return answerService.createAnswer(variant);
    }




}
