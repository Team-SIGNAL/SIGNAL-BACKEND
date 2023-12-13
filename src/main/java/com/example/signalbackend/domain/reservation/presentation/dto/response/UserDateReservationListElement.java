package com.example.signalbackend.domain.reservation.presentation.dto.response;

import com.example.signalbackend.domain.reservation.domain.ReservationStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class UserDateReservationListElement {
    private final UUID id;
    private final String name;
    private final ReservationStatus reservationStatus;
}
