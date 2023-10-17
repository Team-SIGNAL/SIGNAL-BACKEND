package com.example.signalbackend.domain.refresh.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_refresh")
@Entity
public class RefreshToken {

    @Id
    private String accountId;
    private String token;


    public RefreshToken update(String token) {
        this.token = token;
        return this;
    }
}
