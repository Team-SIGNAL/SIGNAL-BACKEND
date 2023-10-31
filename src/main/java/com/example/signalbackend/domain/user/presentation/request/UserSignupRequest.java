package com.example.signalbackend.domain.user.presentation.request;

import com.example.signalbackend.domain.user.domain.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class UserSignupRequest {
    @NotBlank
    private String name;

    @NotBlank
    private LocalDate birth;

    @NotBlank
    private String phone;

    @NotBlank
    private String accountId;

    @NotBlank
    private String password;

    @NotBlank
    private Gender gender;
}
