package server;

import models.Player;

public interface IAuthenticator {
    boolean VerifyLogin(Player player);
    boolean VerifyRegister(Player player);
}
