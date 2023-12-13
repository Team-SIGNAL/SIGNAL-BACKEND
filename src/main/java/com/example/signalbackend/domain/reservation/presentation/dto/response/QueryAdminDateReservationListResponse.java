package com.example.signalbackend.domain.reservation.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class QueryAdminDateReservationListResponse {
    private final List<AdminDateReservationListElement> appointmentList;
}
