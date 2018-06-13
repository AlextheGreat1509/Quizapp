package dbal.context;

import models.Player;

public interface IPlayerContext extends IBaseContext {
    boolean Register(Player player);
    boolean Login(Player player);
}
