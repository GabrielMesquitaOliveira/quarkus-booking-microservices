package org.acme.presentation;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.application.ReservationDto;
import org.acme.application.AvailableSlotDto;
import java.time.LocalDate;
import java.util.List;

@Path("/api/reservations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReservationResource {

    @POST
    public Response createReservation(ReservationDto reservationDto) {
        // TODO: Implement
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/available")
    public Response getAvailableSlots(@QueryParam("date") String dateStr, @QueryParam("resource") String resourceName) {
        // TODO: Implement
        return Response.ok(List.of()).build();
    }
}
