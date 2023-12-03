package com.example.signalbackend.domain.coin.domain;

import com.example.signalbackend.domain.user.domain.User;
import com.example.signalbackend.global.entity.BaseIdEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@SuperBuilder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_coin")
@Entity
public class Coin extends BaseIdEntity {

    private Long coin;

    @Column(columnDefinition = "VARCHAR(11)", nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;

    private LocalDate createDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private Long coinCount;
}
