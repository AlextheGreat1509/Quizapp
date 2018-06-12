package dbal.repositories;

import dbal.context.IPlayerContext;
import dbal.context.IQuestionContext;
import models.Player;
import models.Question;

public class PlayerRepository {
    private final IPlayerContext playerContext;

    public PlayerRepository(IPlayerContext playerContext) {
        this.playerContext = playerContext;
    }

    public boolean Register(Player player){
        return playerContext.Register(player);
    }

    public boolean Login(Player player){
        return playerContext.Login(player);
    }

}
