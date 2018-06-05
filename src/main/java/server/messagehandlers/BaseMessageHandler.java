package server.messagehandlers;

import server.GameSession;

public class BaseMessageHandler {
    GameSession game;

    BaseMessageHandler(GameSession game){
        this.game = game;
    }
}
