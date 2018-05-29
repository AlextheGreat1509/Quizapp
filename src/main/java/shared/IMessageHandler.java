package shared;

public interface IMessageHandler {
    void HandleMessage(String data, String SessionId);
}
