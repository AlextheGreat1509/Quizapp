package server.restserver;

import com.google.gson.Gson;
import models.Player;
import server.Authenticator;
import server.IAuthenticator;
import server.restserver.response.Reply;
import server.restserver.response.Status;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/quiz")
public class QuizApiServices {

    Gson gson = new Gson();
    IAuthenticator iAuthenticator = new Authenticator();

    @POST @Consumes("application/json")
    @Path("/login")
    public javax.ws.rs.core.Response login(String data){
        Reply reply = null;
        Player player = gson.fromJson(data, Player.class);
        reply = new Reply(Status.OK,gson.toJson(iAuthenticator.VerifyLogin(player)));

        return Response.status(reply.getStatus().getCode())
                .entity(reply.getMessage()).build();
    }

    @POST @Consumes("application/json")
    @Path("/register")
    public Response register(String data){
        Reply reply = null;
        Player player = gson.fromJson(data, Player.class);
        reply = new Reply(Status.OK,gson.toJson(iAuthenticator.VerifyRegister(player)));

        return Response.status(reply.getStatus().getCode())
                .entity(reply.getMessage()).build();

    }
}
