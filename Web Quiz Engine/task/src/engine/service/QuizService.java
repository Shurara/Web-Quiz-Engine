package engine.service;

import engine.dao.Quizzes;
import engine.model.Answer;
import engine.model.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    private Quizzes quizzes;

    public List<Quiz> getlist() {
        return quizzes.getList();
    }

    public Quiz getQuizById(int id) {
        return quizzes.getList().stream()
                .filter(x -> x.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Quiz createQuiz(String title, String text, String[]options, int answer){
        return new Quiz(quizzes.getIdCounter(), title, text, options, answer);
    }


    public void addQuiz(List<Quiz> list, Quiz quiz) {
        list.add(quiz);
    }

    public Answer createAnswer(int answerValue, int quizId) {
        Quiz quiz = quizzes.getList().stream()
                .filter(x -> x.getId() == quizId)
                .findFirst()
                .orElse(null);
        return answerValue == quiz.getAnswer()
                ? new Answer(true, "Congratulations, you're right!")
                : new Answer(false, "Wrong answer! Please, try again.");
    }

}
