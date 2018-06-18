package shared.messages;

import models.GameResult;

import java.util.Map;

public class GameResultMessage {
    private GameResult gameResult = new GameResult();

    public GameResultMessage(Map<String, Integer> gameResult) {
        this.gameResult.setResult(gameResult);
    }

    public GameResult getGameResult() {
        return gameResult;
    }
}
