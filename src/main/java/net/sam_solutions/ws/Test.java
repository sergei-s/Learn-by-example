package net.sam_solutions.ws;

import net.sam_solutions.model.PrivateInfoEntity;
import net.sam_solutions.model.PublicInfoEntity;
import net.sam_solutions.model.UsersEntity;
import net.sam_solutions.open_id.OpenIdAuthenticator;
import net.sam_solutions.open_id.OpenIdResponse;
import net.sam_solutions.service.UserService;
import org.openid4java.discovery.DiscoveryInformation;
import org.openid4java.message.AuthRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * User: Sergei Savenko
 * Date: 04.06.12
 */
@Component
@Path("/user")
public class Test {

    @Autowired
    private UserService userService;

    @POST
    @Path("/{login}/{pass}/{name}/{email}")
    public Response createUser(@PathParam("login") String login, @PathParam("pass") String pass,
                               @PathParam("name") String name, @PathParam("email") String email) {
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setLogin(login);
        usersEntity.setPassword(pass);
        PrivateInfoEntity privateInfoEntity = new PrivateInfoEntity();
        privateInfoEntity.setEmail(email);
        usersEntity.setPrivateInfoById(privateInfoEntity);
        privateInfoEntity.setUsersByUserId(usersEntity);
        PublicInfoEntity publicInfoEntity = new PublicInfoEntity();
        publicInfoEntity.setName(name);
        publicInfoEntity.setUsersByUserId(usersEntity);
        usersEntity.setPublicInfoById(publicInfoEntity);
        userService.create(usersEntity);
        UserExists userExists = new UserExists();
        userExists.setValue(userService.checkUser(login));
        return Response.status(200).build();
    }

    @POST
    @Path("/check/{login}")
    @Produces(MediaType.APPLICATION_XML)
    public String checkUser(@PathParam("login") String login) {
        if (login == null) {
            return "not logged in";
        } else if (userService.checkUser(login)) {
            return "registered";
        } else {
            return "not registered";
        }
    }

/*    @POST
    @Path("/openId/{url}")
    @Produces(MediaType.APPLICATION_XML)
    public String openIdRedirect(@PathParam("url") String openIdUrl) {
        OpenIdAuthenticator authenticator = new OpenIdAuthenticator();
        AuthRequest authRequest = authenticator.proceedRedirect(openIdUrl);
        DiscoveryInformation discovered = authenticator.getDiscovered();
        if (authRequest != null) {
            return authRequest.getDestinationUrl(true);
        } else {
            return "http://google.com";
        }
    }*/

}