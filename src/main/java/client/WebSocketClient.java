package client;

import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.net.URI;

public class WebSocketClient implements IWebSocketClient{
    private URI uri = URI.create("ws://localhost:8090/quizapp/");
    private WebSocketContainer container = ContainerProvider.getWebSocketContainer();
    private Session session = null;

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