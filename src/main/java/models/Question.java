package models;

import java.util.ArrayList;

public class Question {
    private ArrayList<Answer> answers;

    public Question(ArrayList<Answer> answers) {
        this.answers = answers;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }
}
