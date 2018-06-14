package client;

import client.messagehandlers.QuestionMessageHandler;
import client.messagehandlers.RoundResultMessageHandler;
import com.google.gson.Gson;
import models.Question;
import models.RoundResult;

public class MessageToObjectClient {
    private Gson gson = new Gson();
    private IUILogic logic = UILogic.getInstance();

    public void processMessage(String type, String data) {
        //Get the last part from the full package and type name
        String simpleType = type.split("\\.")[type.split("\\.").length - 1];

        switch(simpleType)
        {
            case "Question":
                System.out.println("question model");
                QuestionMessageHandler questionMessageHandler = new QuestionMessageHandler(logic);
                questionMessageHandler.HandleQuestion(gson.fromJson(data, Question.class));
                break;
            case "Answer":
                System.out.println(data);
                break;
            case "RoundResult":
                System.out.println("Roundresult received");
                RoundResultMessageHandler roundResultMessageHandler = new RoundResultMessageHandler(logic);
                roundResultMessageHandler.HandleRoundResult(gson.fromJson(data, RoundResult.class));
            default:
        }
    }

}
