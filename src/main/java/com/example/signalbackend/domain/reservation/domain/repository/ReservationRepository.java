package com.example.signalbackend.domain.reservation.domain.repository;

import com.example.signalbackend.domain.reservation.domain.Reservation;
import com.example.signalbackend.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ReservationRepository extends JpaRepository<Reservation, UUID> {
    List<Reservation> findAllByAdmin_IdAndDateOrderByDate(UUID adminId, LocalDate date);
    List<Reservation> findAllByUserAndDateOrderByDate(User user, LocalDate date);
}
