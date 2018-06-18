package models;

import java.util.HashMap;
import java.util.Map;

public class GameResult {
    private Map<String, Integer> gameResult;

    public GameResult() {
        gameResult = new HashMap<String, Integer>();
    }


    public Map<String, Integer> getResult() {
        return gameResult;
    }

    public void setResult(Map<String, Integer> gameResult) {
        this.gameResult = gameResult;
    }
}
