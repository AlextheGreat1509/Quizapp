package server;

import dbal.databaseContext.PlayerDatabaseContext;
import dbal.repositories.PlayerRepository;
import models.Player;

public class Authenticator implements IAuthenticator {

    private PlayerRepository playerRepository;

    public Authenticator() {
        playerRepository = new PlayerRepository(new PlayerDatabaseContext());
    }

    public boolean VerifyLogin(Player player){
        return playerRepository.Login(player);
    }

    public boolean VerifyRegister(Player player){
        return playerRepository.Register(player);
    }
}
