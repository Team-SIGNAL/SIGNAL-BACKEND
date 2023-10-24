package com.example.signalbackend.domain.user.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class UserSignUpRequest {
    @NotBlank
    private String name;

    @NotBlank
    private LocalDate birth;

    @NotNull
    private String phone;

    @NotBlank
    private String accountId;

    @NotBlank
    @Pattern(regexp = "(?=.*[a-z])(?=.*[0-9])(?=.*[!/?@])[a-zA-Z0-9!/" + "?@]{8,12}$",
            message = " ^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$")
    private String password;

    private String gender;
}
