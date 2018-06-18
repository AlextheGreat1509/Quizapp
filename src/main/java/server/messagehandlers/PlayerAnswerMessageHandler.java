package server.messagehandlers;

import models.PlayerAnswer;
import server.GameSession;
import shared.messages.PlayerAnswerMessage;

public class PlayerAnswerMessageHandler extends BaseMessageHandler{

    public PlayerAnswerMessageHandler(GameSession game, String sessionId){
        super(game, sessionId);
    }

    public void HandlePlayerAnswer(PlayerAnswerMessage playerAnswerMessage) {
        game.CheckAnswer(playerAnswerMessage.getAnswer(), sessionId);
    }
}
