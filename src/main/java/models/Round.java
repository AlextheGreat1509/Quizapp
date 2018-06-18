package models;

import java.util.ArrayList;

public class Round {
    private Question question;
    private RoundResult roundResult;

    public Round(Question question) {
        this.question = question;
    }

    public RoundResult getResult() {
        return roundResult;
    }

    public void setResult(RoundResult roundResult) {
        this.roundResult = roundResult;
    }

    public Question getQuestion() {
        return question;
    }
}
