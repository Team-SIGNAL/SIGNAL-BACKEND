package com.example.signalbackend.domain.user.presentation.request;

import com.example.signalbackend.domain.user.domain.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class UserSignupRequest {
    @NotBlank(message = "이름을 입력하세요")
    private String name;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birth;

    @NotBlank(message = "번호를 입력하세요")
    private String phone;

    @NotBlank(message = "아이디를 입력하세요")
    private String accountId;

    @NotBlank(message = "비밀번호를 입력하세요")
    private String password;

    @NotNull
    @Pattern(regexp = "(?=.*[A-Za-z])(?=.*[0-9])(?=.*[!#$%&'()*+,./:;<=>?@＼^_`{|}~])[a-zA-Z0-9!#$%&'()*+,./:;<=>?@＼^_`{|}~]{8,32}$")
    private Gender gender;
}
