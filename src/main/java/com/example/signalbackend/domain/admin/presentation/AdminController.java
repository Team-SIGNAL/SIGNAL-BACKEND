package com.example.signalbackend.domain.admin.presentation;

import com.example.signalbackend.domain.admin.presentation.dto.request.AdminSignUpRequest;
import com.example.signalbackend.domain.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/admin")
@RestController
public class AdminController {

    private final AdminService adminService;
    
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/signup")
    public void adminSignup(@RequestBody @Valid AdminSignUpRequest request) {
        adminService.signup(request);
    }
}
