package server;

import models.Player;
import models.Question;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;

public class AuthenticatorTest {
    @DataPoints
    public Authenticator GetAuthenticator(){
        Authenticator auth = new Authenticator();
        return auth;
    }

    @Test
    public void getRegisterTest(){
        Authenticator auth = GetAuthenticator();
        Assert.assertTrue(auth.VerifyRegister(new Player("Alex", "Alex123")));
    }

    @Test
    public void getLoginTest(){
        Authenticator auth = GetAuthenticator();
        Assert.assertTrue(auth.VerifyLogin(new Player("Alex", "Alex123")));
    }

}
