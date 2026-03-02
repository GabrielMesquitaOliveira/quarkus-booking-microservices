package org.acme.infrastructure;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.domain.User;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {
}
