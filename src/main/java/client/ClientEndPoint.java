package client;

import server.GameSession;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@ClientEndpoint
public class ClientEndPoint{

    @OnOpen
    public void onWebSocketConnect(Session session)
    {
        System.out.println("Socket Connected: " + session);
    }

    @OnMessage
    public void onWebSocketText(String message)
    {

        System.out.println("Received TEXT message: " + message);
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
