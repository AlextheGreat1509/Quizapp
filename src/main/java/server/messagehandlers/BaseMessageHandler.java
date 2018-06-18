package server.messagehandlers;

import server.IGameSession;

public abstract class BaseMessageHandler {
    IGameSession game;
    String sessionId;

    BaseMessageHandler(IGameSession game, String sessionId){
        this.game = game;
        this.sessionId = sessionId;
    }
}
