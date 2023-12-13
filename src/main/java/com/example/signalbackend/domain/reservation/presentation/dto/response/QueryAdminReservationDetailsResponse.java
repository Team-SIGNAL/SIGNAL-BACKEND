package com.example.signalbackend.domain.reservation.presentation.dto.response;

import com.example.signalbackend.domain.reservation.domain.ReservationStatus;
import com.example.signalbackend.domain.user.domain.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@AllArgsConstructor
public class QueryAdminReservationDetailsResponse {
    private final String name;
    private final LocalDate birth;
    private final LocalDate date;
    private final LocalTime time;
    private final String phone;
    private final Gender gender;
    private final ReservationStatus reservationStatus;
    private final String reason;
}
