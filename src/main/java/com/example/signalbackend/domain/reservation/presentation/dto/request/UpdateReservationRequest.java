package com.example.signalbackend.domain.reservation.presentation.dto.request;

import com.example.signalbackend.domain.reservation.domain.ReservationStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class UpdateReservationRequest {

    private String reason;

    @NotNull
    private ReservationStatus reservationStatus;
}
