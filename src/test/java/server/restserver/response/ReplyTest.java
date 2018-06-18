package server.restserver.response;

import com.google.gson.Gson;
import models.Answer;
import org.junit.Assert;
import org.junit.Test;

public class ReplyTest {
    @Test
    public void constructorTest(){
        Reply reply = new Reply(Status.OK, "Test");
        Assert.assertNotNull(reply);
    }

    @Test
    public void valueTest(){
        Reply reply = new Reply(Status.OK, "Test");
        Assert.assertTrue(reply.getStatus() == Status.OK && reply.getMessage().equals("Test"));
    }

    @Test
    public void objectValueTest(){
        Gson gson = new Gson();
        Reply reply = new Reply(Status.OK, new Answer(1, "Test", true));
        Assert.assertTrue(gson.fromJson(reply.getMessage(), Answer.class).getAnswer().equals("Test"));
    }
}
