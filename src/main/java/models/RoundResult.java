package models;

import java.util.HashMap;
import java.util.Map;

public class RoundResult {

    private Map<String, Boolean> roundresult = new HashMap<String, Boolean>();

    public RoundResult() {
    }

    public Map<String, Boolean> getRoundresult() {
        return roundresult;
    }

    public void addResultToRound(String username, Boolean correct){
        roundresult.put(username, correct);
    }
}
