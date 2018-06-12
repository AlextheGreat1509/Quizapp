package models;

import server.GameSession;

import java.util.HashMap;
import java.util.Map;

public class RoundResult {

    private Map<Player, Boolean> roundresult = new HashMap<Player, Boolean>();

    public RoundResult() {
    }

    public Map<Player, Boolean> getRoundresult() {
        return roundresult;
    }

    public void addResultToRound(Player player, Boolean correct){
        roundresult.put(player, correct);
    }
}
