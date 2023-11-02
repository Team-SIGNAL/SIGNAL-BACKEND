package com.example.signalbackend.domain.admin.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class AdminSignUpRequest {

    @NotBlank
    @Size(min = 2, max = 20)
    private String name;

    @NotBlank
    @Size(min = 13)
    private String phone;

    @NotBlank
    @Size(min = 5, max = 12)
    private String accountId;

    @NotBlank
    @Pattern(regexp = "(?=.*[A-Za-z])(?=.*[0-9])(?=.*[!#$%&'()*+,./:;<=>?@＼^_`{|}~])[a-zA-Z0-9!#$%&'()*+,./:;<=>?@＼^_`{|}~]{8,32}$")
    private String password;
}
