package org.acme.infrastructure;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@RegisterRestClient(configKey = "user-api")
public interface UserRestClient {

    @GET
    @Path("/api/users/{id}")
    UserDtoResponse getUserById(@PathParam("id") String id);

    record UserDtoResponse(String id, String username, String email, String fullName) {
    }
}
