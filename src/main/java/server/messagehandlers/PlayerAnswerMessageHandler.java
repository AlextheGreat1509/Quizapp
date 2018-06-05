package server.messagehandlers;

import models.PlayerAnswer;
import server.GameSession;

public class PlayerAnswerMessageHandler extends BaseMessageHandler{

    public PlayerAnswerMessageHandler(GameSession game){
        super(game);
    }

    public void HandlePlayerAnswer(PlayerAnswer playerAnswer) {
        game.CheckAnswer(playerAnswer);
    }
}
