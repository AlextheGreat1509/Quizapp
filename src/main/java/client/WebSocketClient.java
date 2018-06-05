package client;

import java.net.URI;

import javax.websocket.*;

import org.eclipse.jetty.util.component.LifeCycle;

public class WebSocketClient implements IWebSocketClient{
    URI uri = URI.create("ws://localhost:8080/quizapp/");
    WebSocketContainer container = ContainerProvider.getWebSocketContainer();
    Session session = null;

    public void SendMessage(String message){
            try
            {
                if (session == null) {
                    // Attempt Connection
                    session = container.connectToServer(ClientEndPoint.class, uri);
                }
                // Send a message
                session.getBasicRemote().sendText(message);
                // Close session
            }
        catch (Throwable t)
        {
            t.printStackTrace(System.err);
        }
    }
}