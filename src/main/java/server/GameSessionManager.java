package server;

import javax.websocket.Session;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameSessionManager {
    private ArrayList<Session> sessionsList = new ArrayList<>();
    private Map<String,GameSession> gameSessionsMap = new HashMap<String, GameSession>();

    public void addSessionToList(Session session){
        sessionsList.add(session);
        matchSessions();
    }

    public void matchSessions(){
        if (sessionsList.size() >= 1){
            GameSession game = new GameSession(sessionsList);
            for(Session session : sessionsList) {
                gameSessionsMap.put(session.getId(), game);
            }
            sessionsList.clear();
        }
    }

    public GameSession getGameSessionBySession(String sessionId){
        return gameSessionsMap.get(sessionId);
    }
}
