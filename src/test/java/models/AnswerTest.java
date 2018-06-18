package models;

import org.junit.Assert;
import org.junit.Test;

public class AnswerTest {
    @Test
    public void constructorTest(){
        Answer answer = new Answer(1, "test", true);
        Assert.assertNotNull(answer);
    }

    @Test
    public void answerValueTest(){
        Answer answer = new Answer(1, "test", true);
        Assert.assertTrue(answer.getAnswer().equals("test") && answer.getId() == 1 && answer.isCorrect());
    }
}
