package com.example.signalbackend.domain.admin.domain;

import com.example.signalbackend.global.entity.BaseIdEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Builder
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
    @ColumnDefault("admin")
    private String name;

    @Column(columnDefinition = "VARCHAR(13)", nullable = false)
    @ColumnDefault("''")
    private String phone;

    @Column(columnDefinition = "VARCHAR(8)", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public Admin(String adminId, String password, String name, String phone, Role role) {
        this.adminId = adminId;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.role = role;
    }
}
