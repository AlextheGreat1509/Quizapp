package client.authentication;

import com.google.gson.Gson;
import javassist.bytecode.stackmap.TypeData;
import models.Player;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RestClient implements IRestClient {

    Gson gson = new Gson();
    private static final Logger LOGGER = Logger.getLogger( TypeData.ClassName.class.getName() );

    public boolean Login(Player player) {
        HttpPost request = new HttpPost("http://localhost:8090/api/quiz/login");
        return sendAuthenticationRequest(player, request);
    }

    public boolean Register(Player player) {
        HttpPost request = new HttpPost("http://localhost:8090/api/quiz/register");
        return sendAuthenticationRequest(player, request);
    }

    private boolean sendAuthenticationRequest(Player player, HttpPost request){
        String payload = gson.toJson(player);
        StringEntity entity = new StringEntity(payload,
                ContentType.APPLICATION_JSON);

        HttpClient httpClient = HttpClientBuilder.create().build();
        request.setEntity(entity);

        HttpResponse response = null;
        try {
            response = httpClient.execute(request);
            return GetBooleanFromResponse(response);

        } catch (IOException e) {
            LOGGER.log( Level.SEVERE, e.toString(), e );
        }

        return false;
    }
    private Boolean GetBooleanFromResponse(HttpResponse response){
        try {
            BufferedReader rd;
            InputStreamReader stream = new InputStreamReader(response.getEntity().getContent());
            rd = new BufferedReader(stream);

            StringBuilder result = new StringBuilder();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();
            stream.close();
            System.out.println("Json: " + result.toString());
            return gson.fromJson(result.toString(), Boolean.class);

        } catch (IOException e){
            LOGGER.log( Level.SEVERE, e.toString(), e );
        }
        return false;
    }
}
