package com.example.signalbackend.domain.admin.domain;

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
import javax.persistence.Table;

@SuperBuilder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_admin")
@Entity
public class Admin extends BaseIdEntity {

    @Column(columnDefinition = "VARCHAR(20)", nullable = false, unique = true)
    private String adminId;

    @Column(columnDefinition = "CHAR(60)", nullable = false)
    private String password;

    @Column(columnDefinition = "VARCHAR(20)", nullable = false)
    private String name;

    @Column(columnDefinition = "VARCHAR(13)")
    @ColumnDefault("''")
    private String phone;

    @Column(columnDefinition = "VARCHAR(12)", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(columnDefinition = "CHAR(200)")
    private String profile;

    @Column(columnDefinition = "VARCHAR(80)", nullable = false)
    private String address;

    @Column(columnDefinition = "CHAR(200)")
    private String hospitalImage;

    @Column(columnDefinition = "VARCHAR(8)", nullable = false)
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'WAIT'")
    private AuthStatus authStatus;

    public void updateHospitalImage(String hospitalImage, AuthStatus authStatus) {
        this.hospitalImage = hospitalImage;
        this.authStatus = authStatus;
    }

    public void updateAuthStatusAndRole(AuthStatus authSatus, Role role) {
        this.authStatus = authSatus;
        this.role = role;
    }

    public void updateAuthStatus(AuthStatus authSatus) {
        this.authStatus = authSatus;
    }
}
