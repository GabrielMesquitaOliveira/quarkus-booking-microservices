package org.acme.application;

import io.soabase.recordbuilder.core.RecordBuilder;
import java.time.LocalDateTime;

@RecordBuilder
public record AvailableSlotDto(
        LocalDateTime startDate,
        LocalDateTime endDate,
        boolean isAvailable) {
}
