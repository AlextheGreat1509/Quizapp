package server;

import org.junit.Test;
import org.junit.experimental.theories.DataPoints;

public class GameSessionManagerTest {
    @DataPoints
    public GameSession GetGameSession(){
        GameSession game = new GameSession(1);
        return game;
    }

    @Test
    public void getRandomQuestionTest() {
    }
}
