package com.example.signalbackend.domain.reservation.domain;

import com.example.signalbackend.domain.admin.domain.Admin;
import com.example.signalbackend.domain.user.domain.User;
import com.example.signalbackend.global.entity.BaseIdEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalTime;

@SuperBuilder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_reservation")
@Entity
public class Reservation extends BaseIdEntity {

    @Column(columnDefinition = "VARCHAR(1500)", nullable = false)
    @ColumnDefault("''")
    private String reason;

    private LocalDate date;
    private LocalTime time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id")
    private Admin admin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(columnDefinition = "VARCHAR(7)")
    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    public void updateReservationStatus(ReservationStatus reservationStatus, String reason) {
        this.reservationStatus = reservationStatus;
        this.reason = reason;
    }
}
