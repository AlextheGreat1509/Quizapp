package shared.messages;

import models.RoundResult;
import org.junit.Assert;
import org.junit.Test;

public class RoundResultMessageTest {
    @Test
    public void constructorTest(){
        RoundResult roundResult = new RoundResult();
        Assert.assertNotNull(roundResult);
    }

    @Test
    public void valueTest(){
        RoundResult roundResult = new RoundResult();
        roundResult.addResultToRound("Henk", true);
        Assert.assertTrue(roundResult.getRoundresult().get("Henk"));
    }
}
