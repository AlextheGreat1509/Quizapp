package server;

import com.google.gson.Gson;
import models.Answer;
import models.PlayerAnswer;
import server.messagehandlers.PlayerAnswerMessageHandler;

public class MessageToObjectServer {
    Gson gson = new Gson();

    public void processMessage(String sessionId, String type, String data, GameSessionManager gameSessionManager) {
        //Get the last part from the full package and type name
        String simpleType = type.split("\\.")[type.split("\\.").length - 1];

        switch(simpleType)
        {
            case "PlayerAnswer":
                GameSession game = gameSessionManager.getGameSessionBySession(sessionId);
                PlayerAnswerMessageHandler playerAnswerMessageHandler = new PlayerAnswerMessageHandler(game);
                playerAnswerMessageHandler.HandlePlayerAnswer(gson.fromJson(data, PlayerAnswer.class));
            case "Answer":
                System.out.println("nab");
            default:
        }
    }

}
