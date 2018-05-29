package client;

import java.net.URI;

import javax.websocket.*;

import org.eclipse.jetty.util.component.LifeCycle;

public class WebSocketClient implements IWebSocketClient{

    public void SendMessage(String message){
        URI uri = URI.create("ws://localhost:8080/quizapp/");

        try
        {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();

            try
            {
                // Attempt Connect
                Session session = container.connectToServer(ClientEndPoint.class ,uri);
                // Send a message
                session.getBasicRemote().sendText(message);
                // Close session
                session.close();
            }
            finally
            {
                // Force lifecycle stop when done with container.
                // This is to free up threads and resources that the
                // JSR-356 container allocates. But unfortunately
                // the JSR-356 spec does not handle lifecycles (yet)
                if (container instanceof LifeCycle)
                {
                    ((LifeCycle)container).stop();
                }
            }
        }
        catch (Throwable t)
        {
            t.printStackTrace(System.err);
        }
    }
}