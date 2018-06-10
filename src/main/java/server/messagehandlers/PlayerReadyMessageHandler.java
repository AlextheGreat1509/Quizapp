package server.messagehandlers;

import models.PlayerAnswer;
import server.GameSession;
import shared.messages.PlayerReadyMessage;

public class PlayerReadyMessageHandler extends BaseMessageHandler{

    public PlayerReadyMessageHandler(GameSession game, String sessionId){
        super(game, sessionId);
    }

    public void HandlePlayerReady(PlayerReadyMessage playerReadyMessage) {
    }
}
