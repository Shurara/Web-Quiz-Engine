package engine.service;

import engine.model.Quiz;
import org.springframework.stereotype.Service;

@Service
public class QuizService {

    public Quiz createQuiz() {
        return new Quiz("The Java Logo",
                "What is depicted on the Java logo?",
                new String[]{"Robot", "Tea leaf", "Cup of coffee", "Bug"});
    }
}
