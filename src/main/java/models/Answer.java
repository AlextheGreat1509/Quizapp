package models;

public class Answer {
    private int id;
    private String answer;
    private boolean correct;

    public Answer(int id, String answer, boolean correct) {
        this.id = id;
        this.answer = answer;
        this.correct = correct;
    }

    public String getAnswer() {
        return answer;
    }

    public boolean isCorrect() {
        return correct;
    }

    public int getId() {
        return id;
    }
}
