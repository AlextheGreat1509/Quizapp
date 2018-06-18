package server.messagehandlers;

import models.PlayerAnswer;
import server.GameSession;
import server.IGameSession;
import shared.messages.PlayerAnswerMessage;

public class PlayerAnswerMessageHandler extends BaseMessageHandler{

    public PlayerAnswerMessageHandler(IGameSession game, String sessionId){
        super(game, sessionId);
    }

    public void HandlePlayerAnswer(PlayerAnswerMessage playerAnswerMessage) {
        game.CheckAnswer(playerAnswerMessage.getAnswer(), sessionId);
    }
}
