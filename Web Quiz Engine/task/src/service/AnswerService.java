package service;

import model.Answer;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {

    public Answer createAnswer(int value) {
        return value == 2
                ? new Answer(true, "Congratulations, you're right!")
                : new Answer(false, "Wrong answer! Please, try again.");
    }
}
