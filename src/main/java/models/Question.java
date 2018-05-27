package models;

import java.util.ArrayList;

public class Question {
    private int id;
    private String question;
    private ArrayList<Answer> answers;

    public Question(ArrayList<Answer> answers, int id, String question) {
        this.id = id;
        this.answers = answers;
        this.question = question;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }
}
