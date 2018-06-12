package client.authentication;

import models.Player;

public class AuthenticationController implements IAuthenticationController {
    IRestClient iRestClient = new RestClient();

    public boolean Login(String username, String password){
        return iRestClient.Login(new Player(username,password));
    }

    @Override
    public boolean Register(String username, String password) {
        return iRestClient.Register(new Player(username,password));
    }
}
