package server;

import javax.websocket.Session;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class GameSessionManager {
    private ArrayList<Session> sessionsList = new ArrayList<>();
    private Map<String, IGameSession> gameSessionsMap = new HashMap<>();
    private IGameSession gameSession;

    private static final GameSessionManager INSTANCE = new GameSessionManager();

    public void addSessionToList(Session session){
        sessionsList.add(session);
        matchSessions();
    }

    public void matchSessions(){
        if (sessionsList.size() == 1){
            gameSession = new GameSession(2);
            gameSession.addSession(sessionsList.get(0));
            gameSessionsMap.put(sessionsList.get(0).getId(), gameSession);
        }
        if (sessionsList.size() >= 2){
            IGameSession game = gameSession;
            for(Session session : sessionsList) {
                if(!sessionsList.get(0).getId().equals(session.getId())) {
                    gameSession.addSession(session);
                    gameSessionsMap.put(session.getId(), game);
                }
            }
            sessionsList.clear();
        }
    }

    public IGameSession getGameSessionBySession(String sessionId){
        return gameSessionsMap.get(sessionId);
    }

    public static GameSessionManager getInstance(){
        return INSTANCE;
    }
}

