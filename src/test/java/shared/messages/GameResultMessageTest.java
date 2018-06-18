package shared.messages;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class GameResultMessageTest {
    @Test
    public void constructorTest(){
        Map<String, Integer > results = new HashMap<>();
        results.put("Henk", 5);
        GameResultMessage message = new GameResultMessage(results);
        Assert.assertNotNull(message);
    }

    @Test
    public void valueTest(){
        Map<String, Integer > results = new HashMap<>();
        results.put("Henk", 5);
        GameResultMessage message = new GameResultMessage(results);
        Assert.assertTrue(message.getGameResult().getResult() == results);
    }
}
