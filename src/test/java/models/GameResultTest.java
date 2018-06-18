package models;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class GameResultTest {
    @Test
    public void contructorTest(){
        GameResult gameResult = new GameResult();
        Assert.assertNotNull(gameResult);
    }

    @Test
    public void valueTest(){
        GameResult gameResult = new GameResult();
        Map<String, Integer> testResults = new HashMap<>();
        testResults.put("Henk", 5);
        gameResult.setResult(testResults);
        Assert.assertTrue(gameResult.getResult().get("Henk") == testResults.get("Henk"));
    }
}
