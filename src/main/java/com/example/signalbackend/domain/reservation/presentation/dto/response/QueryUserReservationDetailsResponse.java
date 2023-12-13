package com.example.signalbackend.domain.reservation.presentation.dto.response;

import com.example.signalbackend.domain.reservation.domain.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@AllArgsConstructor
public class QueryUserReservationDetailsResponse {
    private final String image;
    private final String name;
    private final String address;
    private final ReservationStatus reservationStatus;
    private final String reason;
    private final LocalDate date;
    private final LocalTime time;
    private final String phone;
}
