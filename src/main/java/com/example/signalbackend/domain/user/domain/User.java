package com.example.signalbackend.domain.user.domain;

import com.example.signalbackend.global.entity.BaseIdEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_user")
@Entity
public class User extends BaseIdEntity {

    @Column(columnDefinition = "VARCHAR(20)", nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate birth;

    @Column(columnDefinition = "VARCHAR(11)", nullable = false, unique = true)
    private String phone;

    @Column(columnDefinition = "VARCHAR(12)", nullable = false, unique = true)
    private String accountId;

    @Column(columnDefinition = "CHAR(255)", nullable = false)
    private String password;

    @Column(columnDefinition = "VARCHAR(5)")
    private String gender;
}
