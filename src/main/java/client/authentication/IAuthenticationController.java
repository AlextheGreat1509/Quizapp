package client.authentication;

public interface IAuthenticationController {
    boolean Login(String username, String password);
    boolean Register(String username, String password);

}
