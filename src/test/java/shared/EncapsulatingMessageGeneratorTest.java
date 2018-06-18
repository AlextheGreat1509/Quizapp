package shared;

import com.google.gson.Gson;
import models.Answer;
import models.Question;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import server.GameSession;
import shared.messages.BaseMessage;
import shared.messages.EncapsulatingMessage;

public class EncapsulatingMessageGeneratorTest {


        @DataPoints
        public IEncapsulatingMessageGenerator getMessageGenerator(){
            IEncapsulatingMessageGenerator messageGenerator = new EncapsulatingMessageGenerator();
            return messageGenerator;
        }

        @Test
        public void generateMessageTest() {
            IEncapsulatingMessageGenerator messageGenerator = getMessageGenerator();
            Assert.assertTrue(messageGenerator.generateMessage(new BaseMessage()) instanceof EncapsulatingMessage);
        }

    @Test
    public void generateMessageStringTest() {
        Gson gson = new Gson();
        IEncapsulatingMessageGenerator messageGenerator = getMessageGenerator();
        Answer answer = new Answer(1, "Test", true);
        String messageString = messageGenerator.generateMessageString(answer);
        EncapsulatingMessage msg = gson.fromJson(messageString, EncapsulatingMessage.class);
        Assert.assertEquals(answer.getAnswer(), gson.fromJson(msg.getMessageData(), Answer.class).getAnswer());
    }
}
