package client.messagehandlers;

import client.IUILogic;
import shared.messages.RoundResultMessage;

public class RoundResultMessageHandler extends BaseMessageHandler {

    public RoundResultMessageHandler(IUILogic logic) {
        super(logic);
    }

    public void HandleRoundResult(RoundResultMessage roundResultMessage){
        logic.setRoundResult(roundResultMessage.getRoundResult());
    }
}
