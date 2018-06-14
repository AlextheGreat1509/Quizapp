package shared.messages;

import models.Player;

public class PlayerReadyMessage extends BaseMessage {
    private Player player;

    public PlayerReadyMessage(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
