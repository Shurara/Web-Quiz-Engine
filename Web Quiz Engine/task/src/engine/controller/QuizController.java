package engine.controller;


import engine.model.Answer;
import engine.model.Quiz;
import engine.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
//@RequestMapping("/api/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;


    @GetMapping("/api/quizzes/{id}")
    public Quiz getQuizById(@PathVariable("id") int quizId) {
        return quizService.getQuizById(quizId);
    }

    @GetMapping("/api/quizzes")
    public List<Quiz> getAllQuizzes() {
        return quizService.getlist();
    }


    @PostMapping("/api/quizzes/{id}/solve")
    public Answer solveQuiz(@RequestParam(name = "answer") int answerValue, @PathVariable("id") int quizId) {
        return quizService.createAnswer(answerValue, quizId);
    }

    @PostMapping("/api/quizzes")
    public Quiz createQuiz(@RequestParam(name = "title") String title,
                           @RequestParam(name = "text") String text,
                           @RequestParam(name = "options") String[] options,
                           @RequestParam(name = "answer") int answer) {
        return quizService.createQuiz(title, text, options, answer);
    }


}
