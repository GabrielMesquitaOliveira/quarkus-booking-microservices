package org.acme.application;

import io.soabase.recordbuilder.core.RecordBuilder;
import org.acme.domain.ReservationStatus;
import java.time.LocalDateTime;

@RecordBuilder
public record ReservationDto(
        String id,
        String userId,
        String username,
        String resourceName,
        LocalDateTime startDate,
        LocalDateTime endDate,
        ReservationStatus status) {
}
