package com.example.signalbackend.domain.reservation.service;

import com.example.signalbackend.domain.admin.domain.Admin;
import com.example.signalbackend.domain.admin.exception.AdminNotFoundException;
import com.example.signalbackend.domain.admin.facade.AdminFacade;
import com.example.signalbackend.domain.reservation.domain.Reservation;
import com.example.signalbackend.domain.reservation.domain.ReservationStatus;
import com.example.signalbackend.domain.reservation.domain.repository.ReservationRepository;
import com.example.signalbackend.domain.reservation.facade.ReservationFacade;
import com.example.signalbackend.domain.reservation.presentation.dto.request.CreateReservationRequest;
import com.example.signalbackend.domain.reservation.presentation.dto.request.UpdateReservationRequest;
import com.example.signalbackend.domain.reservation.presentation.dto.response.AdminDateReservationListElement;
import com.example.signalbackend.domain.reservation.presentation.dto.response.QueryAdminDateReservationListResponse;
import com.example.signalbackend.domain.reservation.presentation.dto.response.QueryAdminReservationDetailsResponse;
import com.example.signalbackend.domain.reservation.presentation.dto.response.QueryUserReservationDetailsResponse;
import com.example.signalbackend.domain.reservation.presentation.dto.response.UserDateReservationListElement;
import com.example.signalbackend.domain.reservation.presentation.dto.response.QueryUserDateReservationListResponse;
import com.example.signalbackend.domain.user.domain.User;
import com.example.signalbackend.domain.user.exception.UserNotFoundException;
import com.example.signalbackend.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationFacade reservationFacade;
    private final AdminFacade adminFacade;
    private final UserFacade userFacade;

    @Transactional
    public void createReservation(UUID hospitalId, CreateReservationRequest request) {
        Admin admin = adminFacade.getAdminById(hospitalId).orElseThrow(() -> AdminNotFoundException.EXCEPTION);
        User user = userFacade.getCurrentUser();

        reservationRepository.save(Reservation.builder()

                        .admin(admin)
                        .date(request.getDate())
                        .time(request.getTime())
                        .user(user)
                        .reason(request.getReason())
                        .reservationStatus(ReservationStatus.WAIT)
                .build());
    }

    @Transactional(readOnly = true)
    public QueryAdminDateReservationListResponse queryAdminDateReservationList(LocalDate date) {
        Admin admin = adminFacade.getCurrentAdmin();

        List<AdminDateReservationListElement> reservationList =
                reservationRepository.findAllByAdmin_IdAndDateOrderByDate(admin.getId(), date).stream()
                .map(reservation -> {
                    User user = userFacade.getUserById(reservation.getUser().getId())
                            .orElseThrow(()-> UserNotFoundException.EXCEPTION);

                    return AdminDateReservationListElement.builder()
                            .id(reservation.getId())
                            .name(user.getName())
                            .birth(user.getBirth())
                            .date(reservation.getDate())
                            .time(reservation.getTime())
                            .reservationStatus(reservation.getReservationStatus())
                            .build();
                }).collect(Collectors.toList());

        return new QueryAdminDateReservationListResponse(reservationList);
    }

    @Transactional(readOnly = true)
    public QueryAdminReservationDetailsResponse queryAdminReservationDetail(UUID reservationId) {
        Reservation reservation = reservationFacade.getReservationById(reservationId);
        User user = userFacade.getUserById(reservation.getUser().getId())
                .orElseThrow(()-> UserNotFoundException.EXCEPTION);

        return new QueryAdminReservationDetailsResponse(
                user.getName(),
                user.getBirth(),
                reservation.getDate(),
                reservation.getTime(),
                user.getPhone(),
                user.getGender(),
                reservation.getReservationStatus(),
                reservation.getReason()
        );
    }

    @Transactional(readOnly = true)
    public QueryUserDateReservationListResponse queryUserDateReservationList(LocalDate date) {
        User user = userFacade.getCurrentUser();

        List<UserDateReservationListElement> reservationList =
                reservationRepository.findAllByUserAndDateOrderByDate(user, date).stream()
                        .map(reservation -> {
                            return UserDateReservationListElement.builder()
                                    .id(reservation.getId())
                                    .name(reservation.getAdmin().getName())
                                    .reservationStatus(reservation.getReservationStatus())
                                    .build();
                        }).collect(Collectors.toList());

        return new QueryUserDateReservationListResponse(reservationList);
    }

    @Transactional(readOnly = true)
    public QueryUserReservationDetailsResponse queryUserReservationDetail(UUID reservationId) {
        Reservation reservation = reservationFacade.getReservationById(reservationId);

        Admin admin = adminFacade.getAdminById(reservation.getAdmin().getId())
                .orElseThrow(() -> AdminNotFoundException.EXCEPTION);

        return new QueryUserReservationDetailsResponse(
                admin.getProfile(),
                admin.getName(),
                admin.getAddress(),
                reservation.getReservationStatus(),
                reservation.getReason(),
                reservation.getDate(),
                reservation.getTime(),
                admin.getPhone()
        );
    }

    @Transactional
    public void updateReservationStatus(UUID reservationId, UpdateReservationRequest request) {
        Reservation reservation = reservationFacade.getReservationById(reservationId);
        reservation.updateReservationStatus(request.getReservationStatus(), request.getReason());
    }
}
