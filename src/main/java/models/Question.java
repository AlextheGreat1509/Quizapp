package models;

import java.util.ArrayList;

public class Question {
    private int id;
    private ArrayList<Answer> answers;

    public Question(ArrayList<Answer> answers, int id) {
        this.id = id;
        this.answers = answers;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public int getId() {
        return id;
    }
}
