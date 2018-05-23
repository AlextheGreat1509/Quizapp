package models;

public class Result {

    private Player player;
    private Answer answer;

    public Result(Player player, Answer answer) {
        this.player = player;
        this.answer = answer;
    }

    public Player getPlayer() {
        return player;
    }

    public Answer getAnswer() {
        return answer;
    }
}
