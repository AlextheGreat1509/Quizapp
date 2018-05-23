package models;

public class RoundResult {
    private Player player;
    private boolean correct;

    public RoundResult(Player player, boolean correct) {
        this.player = player;
        this.correct = correct;
    }

    public boolean isCorrect() {
        return correct;
    }

    public Player getPlayer() {
        return player;
    }
}
