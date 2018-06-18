package server.messagehandlers;

import server.IGameSession;
import shared.messages.PlayerReadyMessage;

public class PlayerReadyMessageHandler extends BaseMessageHandler{

    public PlayerReadyMessageHandler(IGameSession game, String sessionId){
        super(game, sessionId);
    }

    public void HandlePlayerReady(PlayerReadyMessage playerReadyMessage) {
        game.addPlayerSession(playerReadyMessage.getPlayer(),sessionId);
    }
}
