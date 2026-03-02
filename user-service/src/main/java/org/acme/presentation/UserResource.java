package org.acme.presentation;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.application.UserDto;
import java.util.List;

@Path("/api/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    @POST
    public Response createUser(UserDto userDto) {
        // TODO: Implement
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/{id}")
    public Response getUser(@PathParam("id") String id) {
        // TODO: Implement
        return Response.ok().build();
    }

    @GET
    public Response getAllUsers() {
        // TODO: Implement
        return Response.ok(List.of()).build();
    }
}
