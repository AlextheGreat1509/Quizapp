package models;

import org.junit.Assert;
import org.junit.Test;

public class PlayerTest {
    @Test
    public void constructorTest(){
        Player player = new Player("Henk", "Henk123");
        Assert.assertNotNull(player);
    }
}
