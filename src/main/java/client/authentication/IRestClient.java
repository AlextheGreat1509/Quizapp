package client.authentication;

import models.Player;

public interface IRestClient {
    boolean Login(Player player);
    boolean Register(Player player);
}
