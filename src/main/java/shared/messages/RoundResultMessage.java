package shared.messages;

import models.RoundResult;

public class RoundResultMessage extends BaseMessage {
    private RoundResult roundResult;

    public RoundResultMessage(RoundResult roundResult) {
        this.roundResult = roundResult;
    }

    public RoundResult getRoundResult() {
        return roundResult;
    }
}
