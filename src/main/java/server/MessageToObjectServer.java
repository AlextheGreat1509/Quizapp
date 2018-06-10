package server;

import com.google.gson.Gson;
import models.Answer;
import models.PlayerAnswer;
import server.messagehandlers.PlayerAnswerMessageHandler;
import server.messagehandlers.PlayerReadyMessageHandler;
import shared.messages.PlayerReadyMessage;

public class MessageToObjectServer {
    Gson gson = new Gson();

    public void processMessage(String sessionId, String type, String data, GameSessionManager gameSessionManager) {
        //Get the last part from the full package and type name
        String simpleType = type.split("\\.")[type.split("\\.").length - 1];
        GameSession game = gameSessionManager.getGameSessionBySession(sessionId);

        switch(simpleType)
        {
            case "PlayerAnswer":
                PlayerAnswerMessageHandler playerAnswerMessageHandler = new PlayerAnswerMessageHandler(game, sessionId);
                playerAnswerMessageHandler.HandlePlayerAnswer(gson.fromJson(data, PlayerAnswer.class));
                break;
            case "PlayerReadyMessage":
                PlayerReadyMessageHandler playerReadyMessageHandler = new PlayerReadyMessageHandler(game, sessionId);
                playerReadyMessageHandler.HandlePlayerReady(gson.fromJson(data, PlayerReadyMessage.class));
                break;
            default:
        }
    }

}
