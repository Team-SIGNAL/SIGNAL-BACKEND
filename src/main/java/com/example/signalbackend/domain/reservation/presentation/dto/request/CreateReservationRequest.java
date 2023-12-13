package com.example.signalbackend.domain.reservation.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@NoArgsConstructor
public class CreateReservationRequest {
    @NotBlank
    private String reason;
    @NotNull
    private LocalDate date;
    @NotNull
    private LocalTime time;
}
