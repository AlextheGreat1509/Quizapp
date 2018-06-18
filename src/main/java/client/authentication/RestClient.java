package client.authentication;

import com.google.gson.Gson;
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

public class RestClient implements IRestClient {

    Gson gson = new Gson();

    public boolean Login(Player player) {
        String payload = gson.toJson(player);
        StringEntity entity = new StringEntity(payload,
                ContentType.APPLICATION_JSON);

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost("http://localhost:8090/api/quiz/login");
        request.setEntity(entity);

        HttpResponse response = null;
        try {
            response = httpClient.execute(request);
            return GetBooleanFromResponse(response);

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(response.getStatusLine().getStatusCode() + " " + response.getStatusLine().getReasonPhrase());


        return false;
    }

    public boolean Register(Player player) {
        String payload = gson.toJson(player);
        StringEntity entity = new StringEntity(payload,
                ContentType.APPLICATION_JSON);

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost("http://localhost:8090/api/quiz/register");
        request.setEntity(entity);

        HttpResponse response = null;
        try {
            response = httpClient.execute(request);
            return GetBooleanFromResponse(response);

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(response.getStatusLine().getStatusCode() + " " + response.getStatusLine().getReasonPhrase());

        return false;
    }

    private Boolean GetBooleanFromResponse(HttpResponse response){
        try {
            BufferedReader rd;
            rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));

            StringBuilder result = new StringBuilder();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();
            System.out.println("Json: " + result.toString());
            return gson.fromJson(result.toString(), Boolean.class);

        } catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }
}
