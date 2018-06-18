package client.messagehandlers;

import client.IUILogic;
import shared.messages.GameResultMessage;
import shared.messages.RoundResultMessage;

public class GameResultMessageHandler extends BaseMessageHandler {

    public GameResultMessageHandler(IUILogic logic) {
        super(logic);
    }

    public void HandleGameResult(GameResultMessage gameResultMessage){
        logic.setGameResult(gameResultMessage.getGameResult());
    }
}
