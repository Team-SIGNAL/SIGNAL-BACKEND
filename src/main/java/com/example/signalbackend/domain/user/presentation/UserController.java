package com.example.signalbackend.domain.user.presentation;

import com.example.signalbackend.domain.user.presentation.dto.request.UserSignInRequest;
import com.example.signalbackend.domain.user.presentation.dto.request.UserSignUpRequest;
import com.example.signalbackend.domain.user.presentation.dto.response.TokenResponse;
import com.example.signalbackend.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {
    private final UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public void signup(@RequestBody UserSignUpRequest request) {
        userService.signUp(request);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signin")
    public TokenResponse signin(@RequestBody UserSignInRequest request) {
        return userService.singIn(request);
    }
}
