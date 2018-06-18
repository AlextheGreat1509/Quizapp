package client;

public interface IWebSocketClient {
    /**
     * @param message
     * Send a message to the server
     */
    void SendMessage(String message);
}
