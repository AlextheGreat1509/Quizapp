package server;

import models.Question;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;

public class GameSessionTest {

    @DataPoints
    public GameSession GetGameSession(){
        GameSession game = new GameSession(1);
        return game;
    }

    @Test
    public void getRandomQuestionTest(){
        GameSession game = GetGameSession();
        Assert.assertTrue(game.PrepareRandomQuestion() instanceof Question);
    }

}

