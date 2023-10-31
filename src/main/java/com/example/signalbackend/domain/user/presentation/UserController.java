package com.example.signalbackend.domain.user.presentation;

import com.example.signalbackend.domain.user.presentation.request.UserSigninRequest;
import com.example.signalbackend.domain.user.presentation.request.UserSignupRequest;
import com.example.signalbackend.domain.user.service.UserService;
import com.example.signalbackend.global.utils.token.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public void userSignup(@RequestBody @Valid UserSignupRequest request) {
        userService.userSignup(request);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/singin")
    public TokenResponse userSignin(@RequestBody @Valid UserSigninRequest request) {
        return userService.userSignin(request);
    }
}
