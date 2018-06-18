package models;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;

import java.util.ArrayList;

public class RoundTest {
    @DataPoints
    public Round getRound(){
        ArrayList<Answer> answers = new ArrayList<>();
        answers.add(new Answer(1, "Test", true));
        Question question = new Question(answers, 1 , "Test question");
        return new Round(question);
    }
    @Test
    public void constructorTest(){
        Round round = getRound();
        Assert.assertNotNull(round);
    }

    @Test
    public void valueTest(){
        ArrayList<Answer> answers = new ArrayList<>();
        answers.add(new Answer(1, "Test", true));
        Question question = new Question(answers, 1 , "Test question");
        Round round = new Round(question);
        Assert.assertTrue(round.getQuestion().getAnswers() == answers && round.getQuestion() ==question);
    }

    @Test
    public void roundResultTest(){
        Round round = getRound();
        RoundResult roundResult = new RoundResult();
        roundResult.addResultToRound("Henk", true);
        round.setResult(roundResult);
        Assert.assertTrue(roundResult == round.getResult());
    }
}
