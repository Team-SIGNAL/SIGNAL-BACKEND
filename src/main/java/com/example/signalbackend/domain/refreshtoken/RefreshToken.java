package com.example.signalbackend.domain.refreshtoken;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "tbl_refresh_token")
@Entity
public class RefreshToken {

    @Id
    private String id;

    private String refreshToken;

    public void update(String token) {
        this.refreshToken = token;
    }
}
