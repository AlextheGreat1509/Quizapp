package server;

import com.google.gson.Gson;
import shared.EncapsulatingMessageGenerator;
import shared.IEncapsulatingMessageGenerator;
import shared.messages.EncapsulatingMessage;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;

@ClientEndpoint
@ServerEndpoint(value="/quizapp/")
public class WebSocket implements IWebSocket{
    private static ArrayList<Session> sessions = new ArrayList<>();
    private Gson gson = new Gson();
    private IGameSessionManager gameSessionManager;
    private MessageToObjectServer messageToObjectServer;
    private IEncapsulatingMessageGenerator messageGenerator;

    public WebSocket() {
        messageGenerator = new EncapsulatingMessageGenerator();
        messageToObjectServer = new MessageToObjectServer();
        gameSessionManager = GameSessionManager.getInstance();
    }

    @OnOpen
    public void onWebSocketConnect(Session session)
    {
        System.out.println("Socket Connected: " + session);
        sessions.add(session);
        gameSessionManager.addSessionToList(session);

    }

    @OnMessage
    public void onWebSocketText(String message, Session session)
    {
        String sessionId = session.getId();
        EncapsulatingMessage encapMsg = gson.fromJson(message,EncapsulatingMessage.class);
        messageToObjectServer.processMessage(sessionId ,encapMsg.getMessageType(),encapMsg.getMessageData(), gameSessionManager);
    }

    public void sendTo(String sessionId, Object object)
    {
        String msg = messageGenerator.generateMessageString(object);
        sendToClient(getSessionFromId(sessionId), msg);
    }

    public Session getSessionFromId(String sessionId)
    {
        for(Session s : sessions)
        {
            if(s.getId().equals(sessionId))
                return s;
        }
        return null;
    }


    public void broadcast(Object object)
    {
        for(Session session : sessions) {
            sendTo(session.getId(), object);
        }
    }

    private void sendToClient(Session session, String message)
    {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }



    @OnClose
    public void onWebSocketClose(CloseReason reason)
    {
        System.out.println("Socket Closed: " + reason);
    }

    @OnError
    public void onWebSocketError(Throwable cause)
    {
        cause.printStackTrace(System.err);
    }
}
