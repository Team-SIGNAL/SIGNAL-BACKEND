package com.example.signalbackend.domain.user.domain;

import com.example.signalbackend.global.entity.BaseIdEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@SuperBuilder
@Table(name = "tbl_user")
@Entity
public class User extends BaseIdEntity {

    @Column(columnDefinition = "VARCHAR(20)", nullable = false, unique = true)
    private String accountId;

    @Column(columnDefinition = "CHAR(60)", nullable = false)
    private String password;

    @Column(columnDefinition = "VARCHAR(20)", nullable = false)
    private String name;

    @Column(columnDefinition = "DATE", nullable = false)
    private LocalDate birth;

    @Column(columnDefinition = "VARCHAR(13)", nullable = false)
    private String phone;

    @Column(columnDefinition = "VARCHAR(5)", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(columnDefinition = "CHAR(45)")
    private String profile;
}
