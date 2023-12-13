package com.example.signalbackend.domain.reservation.presentation;

import com.example.signalbackend.domain.reservation.domain.ReservationStatus;
import com.example.signalbackend.domain.reservation.presentation.dto.request.CreateReservationRequest;
import com.example.signalbackend.domain.reservation.presentation.dto.request.UpdateReservationRequest;
import com.example.signalbackend.domain.reservation.presentation.dto.response.QueryAdminDateReservationListResponse;
import com.example.signalbackend.domain.reservation.presentation.dto.response.QueryAdminReservationDetailsResponse;
import com.example.signalbackend.domain.reservation.presentation.dto.response.QueryUserDateReservationListResponse;
import com.example.signalbackend.domain.reservation.presentation.dto.response.QueryUserReservationDetailsResponse;
import com.example.signalbackend.domain.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.time.LocalDate;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/reservation")
@RestController
public class ReservationController {

    private final ReservationService reservationService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{hospital_id}")
    public void createReservation(
            @PathVariable(name = "hospital_id") UUID hospitalId,
            @RequestBody CreateReservationRequest request
    ) {
        reservationService.createReservation(hospitalId, request);
    }

    @GetMapping("/admin")
    public QueryAdminDateReservationListResponse queryAdminDateReservationList(
            @PathParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date ) {
        return reservationService.queryAdminDateReservationList(date);
    }

    @GetMapping("/admin/detail/{reservation_id}")
    public QueryAdminReservationDetailsResponse queryAdminReservationDetail(
            @PathVariable(name = "reservation_id") UUID reservationId
    ) {
        return reservationService.queryAdminReservationDetail(reservationId);
    }

    @GetMapping("/user")
    public QueryUserDateReservationListResponse queryUserDateReservationList(
            @PathParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date
    ) {
        return reservationService.queryUserDateReservationList(date);
    }

    @GetMapping("/user/detail/{reservation_id}")
    public QueryUserReservationDetailsResponse queryUserReservationDetail(@PathVariable(name = "reservation_id") UUID reservation_id) {
        return reservationService.queryUserReservationDetail(reservation_id);
    }

    @PatchMapping("/{reservation_id}")
    public void updateReservationStatus(
            @PathVariable(name = "reservation_id") UUID reservation_id,
            @RequestBody UpdateReservationRequest request
    ) {
        reservationService.updateReservationStatus(reservation_id, request);
    }
}
