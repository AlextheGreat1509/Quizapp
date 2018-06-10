package server.messagehandlers;

import server.GameSession;

public abstract class BaseMessageHandler {
    GameSession game;
    String sessionId;

    BaseMessageHandler(GameSession game, String sessionId){
        this.game = game;
        this.sessionId = sessionId;
    }
}
