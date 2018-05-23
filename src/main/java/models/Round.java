package models;

import java.util.ArrayList;

public class Round {
    private Question question;
    private ArrayList<PlayerAnswer> playerAnswers;
    private RoundResult roundResult;

    public Round(Question question) {
        this.question = question;
    }

    public ArrayList<PlayerAnswer> getPlayerAnswers() {
        return playerAnswers;
    }

    public void setPlayerAnswers(ArrayList<PlayerAnswer> playerAnswers) {
        this.playerAnswers = playerAnswers;
    }

    public RoundResult getRoundResult() {
        return roundResult;
    }

    public void setRoundResult(RoundResult roundResult) {
        this.roundResult = roundResult;
    }

    public Question getQuestion() {
        return question;
    }
}
