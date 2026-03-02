package org.acme.infrastructure;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.domain.Reservation;

@ApplicationScoped
public class ReservationRepository implements PanacheRepository<Reservation> {
}
