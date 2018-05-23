package models;

public class PlayerAnswer {

    private Player player;
    private Answer answer;

    public PlayerAnswer(Player player, Answer answer) {
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
