package org.acme.application;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class GetUserUseCase {

    public UserDto getUserById(String id) {
        // TODO: Implement
        return null; // Stub
    }

    public List<UserDto> getAllUsers() {
        // TODO: Implement
        return List.of(); // Stub
    }
}
