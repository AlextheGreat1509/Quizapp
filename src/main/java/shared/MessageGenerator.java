package shared;

import com.google.gson.Gson;

public class MessageGenerator {
    Gson gson = new Gson();

    public String GenerateMessage(Object object){
        Class messageClass = object.getClass();
        return gson.toJson(object);
    }
}
