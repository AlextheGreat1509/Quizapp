package client;

import client.messagehandlers.GameResultMessageHandler;
import client.messagehandlers.QuestionMessageHandler;
import client.messagehandlers.RoundResultMessageHandler;
import com.google.gson.Gson;
import models.Question;
import models.RoundResult;
import shared.messages.GameResultMessage;
import shared.messages.QuestionMessage;
import shared.messages.RoundResultMessage;

public class MessageToObjectClient {
    private Gson gson = new Gson();
    private IUILogic logic = UILogic.getInstance();

    public void processMessage(String type, String data) {
        //Get the last part from the full package and type name
        String simpleType = type.split("\\.")[type.split("\\.").length - 1];

        switch(simpleType)
        {
            case "QuestionMessage":
                System.out.println("question model");
                QuestionMessageHandler questionMessageHandler = new QuestionMessageHandler(logic);
                questionMessageHandler.HandleQuestion(gson.fromJson(data, QuestionMessage.class));
                break;
            case "GameResultMessage":
                GameResultMessageHandler gameResultMessageHandler = new GameResultMessageHandler(logic);
                gameResultMessageHandler.HandleGameResult(gson.fromJson(data, GameResultMessage.class));
                break;
            case "RoundResultMessage":
                System.out.println("Roundresult received");
                RoundResultMessageHandler roundResultMessageHandler = new RoundResultMessageHandler(logic);
                roundResultMessageHandler.HandleRoundResult(gson.fromJson(data, RoundResultMessage.class));
            default:
        }
    }

}
