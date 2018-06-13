package client;

import com.google.gson.Gson;
import shared.messages.EncapsulatingMessage;

import javax.websocket.*;

@ClientEndpoint
public class ClientEndPoint{
    private Gson gson = new Gson();
    private MessageToObjectClient messageToObjectClient;

    public ClientEndPoint() {
        messageToObjectClient = new MessageToObjectClient();
    }

    @OnOpen
    public void onWebSocketConnect(Session session)
    {
        System.out.println("Socket Connected: " + session);
    }

    @OnMessage
    public void onWebSocketText(String message)
    {
        EncapsulatingMessage encapMsg = gson.fromJson(message,EncapsulatingMessage.class);
        System.out.println("Received TEXT message: " + encapMsg.getMessageType());
        messageToObjectClient.processMessage(encapMsg.getMessageType(),encapMsg.getMessageData());
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
