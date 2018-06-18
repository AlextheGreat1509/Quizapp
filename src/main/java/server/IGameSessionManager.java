package server;

import javax.websocket.Session;

public interface IGameSessionManager {
    void addSessionToList(Session session);
    IGameSession getGameSessionBySession(String sessionId);
}
