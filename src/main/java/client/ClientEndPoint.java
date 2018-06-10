package client;

import com.google.gson.Gson;
import shared.messages.EncapsulatingMessage;

import javax.websocket.*;

@ClientEndpoint
public class ClientEndPoint{
    Gson gson = new Gson();
    MessageToObjectClient messageToObjectClient = new MessageToObjectClient();

    @OnOpen
    public void onWebSocketConnect(Session session)
    {
        System.out.println("Socket Connected: " + session);
    }

    @OnMessage
    public void onWebSocketText(String message)
    {
        EncapsulatingMessage encapMsg = gson.fromJson(message,EncapsulatingMessage.class);
        messageToObjectClient.processMessage(encapMsg.getMessageType(),encapMsg.getMessageData());
        System.out.println("Received TEXT message: " + encapMsg.getMessageType());
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
