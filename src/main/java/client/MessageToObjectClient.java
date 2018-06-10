package client;

import client.messagehandlers.QuestionMessageHandler;
import com.google.gson.Gson;
import frontend.QuestionFX;
import models.Question;

public class MessageToObjectClient {
    Gson gson = new Gson();
    IUILogic logic = UILogic.getInstance();

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
            default:
        }
    }

}
