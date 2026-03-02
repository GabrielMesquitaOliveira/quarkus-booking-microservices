package org.acme.application;

import jakarta.enterprise.context.ApplicationScoped;
import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class GetAvailableSlotsUseCase {

    public List<AvailableSlotDto> execute(LocalDate date, String resourceName) {
        // TODO: Implement
        return List.of(); // Stub
    }
}
