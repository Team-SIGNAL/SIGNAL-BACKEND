package com.example.signalbackend.domain.reservation.facade;

import com.example.signalbackend.domain.reservation.domain.Reservation;
import com.example.signalbackend.domain.reservation.domain.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class ReservationFacade {

    private final ReservationRepository reservationRepository;

    public Reservation getReservationById(UUID reservationId) {
        return reservationRepository.findById(reservationId).orElseThrow();
    }
}
