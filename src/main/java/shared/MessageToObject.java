package shared;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class MessageToObject<T> {
    Gson gson = new Gson();

    public Object TranslateMessage(String message){
        Type type = ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        T msg = gson.fromJson(message, type);
        return msg;
    }
}
