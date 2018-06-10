package server;

import models.Answer;
import models.PlayerAnswer;
import models.Question;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;

import javax.websocket.Session;
import java.util.ArrayList;

public class GameSessionTest {

    @DataPoints
    public GameSession GetGameSession(){
        ArrayList<Session> sessions = new ArrayList<Session>();
        return new GameSession(sessions);
    }

    /*@Test
    public void CheckAnswerTest(){
        GameSession game = GetGameSession();
        Answer answer = new Answer(1, "Test123", true);
        PlayerAnswer playerAnswer = new PlayerAnswer(answer);
        Assert.assertEquals(game.CheckAnswer(playerAnswer,),playerAnswer.getAnswer().isCorrect());
    } */

    @Test
    public void getRandomQuestionTest(){
        GameSession game = GetGameSession();
        Assert.assertTrue(game.PrepareRandomQuestion() instanceof Question);;
    }
}

