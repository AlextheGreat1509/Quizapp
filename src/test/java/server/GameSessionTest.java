package server;

import models.Answer;
import models.Question;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;

public class GameSessionTest {

    @DataPoints
    public GameSession GetGameSession(){
        return new GameSession();
    }

    @Test
    public void CheckAnswerTest(){
        GameSession game = GetGameSession();
        Answer answer = new Answer(1, "Test123", true);
        Assert.assertEquals(game.CheckAnswer(answer),answer.isCorrect());
    }

    @Test
    public void getRandomQuestionTest(){
        GameSession game = GetGameSession();
        Assert.assertTrue(game.PrepareRandomQuestion() instanceof Question);;
    }
}

