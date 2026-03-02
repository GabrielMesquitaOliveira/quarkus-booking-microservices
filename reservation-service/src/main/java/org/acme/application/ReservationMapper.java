package org.acme.application;

import org.acme.domain.Reservation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface ReservationMapper {

    ReservationDto toDto(Reservation reservation);

    Reservation toEntity(ReservationDto dto);
}
