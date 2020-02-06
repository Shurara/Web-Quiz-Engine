package engine.dao;

import engine.model.Quiz;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


public class Quizzes {
    private Quiz quiz1 = new Quiz(1, "The Java Logo",
            "What is depicted on the Java logo?",
            new String[]{"Robot", "Tea leaf", "Cup of coffee", "Bug"}, 2);
    private Quiz quiz2 = new Quiz(2, "The Ultimate Question",
            "What is the answer to the Ultimate Question of Life, the Universe and Everything?",
            new String[]{"Everything goes right", "42", "2+2=4", "11011100"}, 0);

    private int idCounter;

    public List<Quiz> list = new ArrayList<>(){
        {
            list.add(quiz1);
            list.add(quiz2);
            setIdCounter(list.size());
        }
    };

    public List<Quiz> getList() {
        return list;
    }

    public void setList(List<Quiz> list) {
        this.list = list;
    }

    public int getIdCounter() {
        return idCounter;
    }

    public void setIdCounter(int idCounter) {
        this.idCounter = idCounter;
    }
}
