package shared.messages;

import models.Answer;
import org.junit.Assert;
import org.junit.Test;

public class PlayerAnswerMessageTest {
    @Test
    public void constructorTest(){
        PlayerAnswerMessage message = new PlayerAnswerMessage(new Answer(1, "Test", true));
        Assert.assertNotNull(message);
    }

    @Test
    public void valueTest(){
        Answer answer = new Answer(1, "Test", true);
        PlayerAnswerMessage message = new PlayerAnswerMessage(answer);
        Assert.assertTrue(message.getAnswer() == answer);
    }
}
