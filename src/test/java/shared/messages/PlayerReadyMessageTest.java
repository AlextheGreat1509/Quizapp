package shared.messages;

import models.Player;
import org.junit.Assert;
import org.junit.Test;

public class PlayerReadyMessageTest {
    @Test
    public void constructorTest(){
        Player player = new Player("Henk", "Henk123");
        PlayerReadyMessage playerReadyMessage = new PlayerReadyMessage(player);
        Assert.assertNotNull(playerReadyMessage);
    }

    @Test
    public void valueTest(){
        Player player = new Player("Henk", "Henk123");
        PlayerReadyMessage playerReadyMessage = new PlayerReadyMessage(player);
        Assert.assertTrue(playerReadyMessage.getPlayer() == player);
    }
}
