package server;

import models.Answer;
import models.Player;

import javax.websocket.Session;

public interface IGameSession {
    boolean addSession(Session session);
    boolean addPlayerSession(Player player, String sessionId);
    void CheckAnswer(Answer answer, String sessionId);
}
