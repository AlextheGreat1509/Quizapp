package dbal.context;

import models.Player;
import models.Question;

public interface IPlayerContext extends IBaseContext {
    boolean Register(Player player);
    boolean Login(Player player);
}
