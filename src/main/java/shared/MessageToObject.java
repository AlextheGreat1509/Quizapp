package shared;

import com.google.gson.Gson;
import models.Answer;

public class MessageToObject {
    Gson gson = new Gson();

    public Object processMessage(String sessionId, String type, String data) {
        //Get the last part from the full package and type name
        String simpleType = type.split("\\.")[type.split("\\.").length - 1];

        switch(simpleType)
        {
            case "Answer":
                return gson.fromJson(data, Answer.class);
            default:
                return null;
        }
    }

}
