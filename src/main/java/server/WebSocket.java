package server;

import com.google.gson.Gson;
import models.Answer;
import shared.EncapsulatingMessageGenerator;
import shared.MessageToObject;
import shared.messages.EncapsulatingMessage;

import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;

@ClientEndpoint
@ServerEndpoint(value="/quizapp/")
public class WebSocket implements IWebSocket{
    private static ArrayList<Session> sessions = new ArrayList<>();
    Gson gson = new Gson();
    //MessageGenerator messageGenerator = new MessageGenerator();
    MessageToObject messageToObject = new MessageToObject();
    EncapsulatingMessageGenerator messageGenerator = new EncapsulatingMessageGenerator();

    @OnOpen
    public void onWebSocketConnect(Session session)
    {
        System.out.println("Socket Connected: " + session);
        sessions.add(session);
    }

    @OnMessage
    public void onWebSocketText(String message, Session session)
    {
        String sessionId = session.getId();
        EncapsulatingMessage encapMsg = gson.fromJson(message,EncapsulatingMessage.class);
        Answer answer = (Answer) messageToObject.processMessage(sessionId ,encapMsg.getMessageType(),encapMsg.getMessageData());
        GameSession game = new GameSession();
        Boolean result = game.CheckAnswer(answer);
        sendTo(session.getId(),answer);
        System.out.println("Answer result: " + result);
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
