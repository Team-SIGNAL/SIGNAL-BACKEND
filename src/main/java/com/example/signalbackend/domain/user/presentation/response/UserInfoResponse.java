package com.example.signalbackend.domain.user.presentation.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class UserInfoResponse {
    private String name;
    private String phone;
    private LocalDate birth;
    private String profile;
}
