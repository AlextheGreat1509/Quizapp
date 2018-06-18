package server;

import org.junit.Test;
import org.junit.experimental.theories.DataPoints;

public class GameSessionManagerTest {
    @DataPoints
    public GameSessionManager GetGameSession(){
        GameSessionManager game = GameSessionManager.getInstance();
        return game;
    }
}
