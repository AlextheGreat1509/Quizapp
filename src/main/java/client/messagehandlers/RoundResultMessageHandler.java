package client.messagehandlers;

import client.IUILogic;
import models.Question;
import models.RoundResult;

public class RoundResultMessageHandler extends BaseMessageHandler {

    public RoundResultMessageHandler(IUILogic logic) {
        super(logic);
    }

    public void HandleRoundResult(RoundResult roundResult){
        logic.setRoundResult(roundResult);
    }
}
