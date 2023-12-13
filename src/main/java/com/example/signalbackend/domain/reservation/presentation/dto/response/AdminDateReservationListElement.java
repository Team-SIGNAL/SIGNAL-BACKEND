package com.example.signalbackend.domain.reservation.presentation.dto.response;

import com.example.signalbackend.domain.reservation.domain.ReservationStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Builder
public class AdminDateReservationListElement {
    private final UUID id;
    private final String name;
    private final LocalDate birth;
    private final LocalDate date;
    private final LocalTime time;
    private final ReservationStatus reservationStatus;
}
