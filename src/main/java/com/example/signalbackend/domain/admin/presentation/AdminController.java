package com.example.signalbackend.domain.admin.presentation;

import com.example.signalbackend.domain.admin.presentation.dto.request.HospitalSignInRequest;
import com.example.signalbackend.domain.admin.presentation.dto.request.HospitalSignUpRequest;
import com.example.signalbackend.domain.admin.presentation.dto.request.UpdateHospitalAuthStatusRequest;
import com.example.signalbackend.domain.admin.presentation.dto.request.UpdateHospitalImageRequest;
import com.example.signalbackend.domain.admin.presentation.dto.response.HospitalAuthDetailsResponse;
import com.example.signalbackend.domain.admin.presentation.dto.response.HospitalAuthRequestListResponse;
import com.example.signalbackend.domain.admin.presentation.dto.response.HospitalInfoResponse;
import com.example.signalbackend.domain.admin.presentation.dto.response.AdminTokenResponse;
import com.example.signalbackend.domain.admin.presentation.dto.response.HospitalListResponse;
import com.example.signalbackend.domain.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/admin")
@RestController
public class AdminController {

    private final AdminService adminService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public void adminSignup(@RequestBody @Valid HospitalSignUpRequest request) {
        adminService.signup(request);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signin")
    public AdminTokenResponse adminSignin(@RequestBody @Valid HospitalSignInRequest request) {
        return adminService.signin(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/image")
    public void updateAdminHospitalImage(@RequestBody @Valid UpdateHospitalImageRequest request) {
        adminService.updateHospitalImage(request);
    }

    @GetMapping("/info")
    public HospitalInfoResponse queryAdminInfo() {
        return adminService.queryAdminInfo();
    }

    @DeleteMapping("/secession")
    public void adminSecession() {
        adminService.adminSecession();
    }

    @GetMapping("/list")
    public HospitalAuthRequestListResponse queryHospitalAuthRequestList() {
        return adminService.queryHospitalAuthRequestList();
    }

    @GetMapping("/{hospital_id}")
    public HospitalAuthDetailsResponse queryHospitalAuthDetails(@PathVariable(name = "hospital_id") UUID hospitalId) {
        return adminService.queryHospitalAuthDetails(hospitalId);
    }

    @PatchMapping("/auth/{hospital_id}")
    public void updateHospitalAuthStatus(
            @PathVariable(name = "hospital_id") UUID hospitalId,
            @RequestBody UpdateHospitalAuthStatusRequest request
    ) {
        adminService.updateHospitalAuthStatus(hospitalId, request);
    }

    @GetMapping("/hospital/list")
    public HospitalListResponse queryHospitalList() {
        return adminService.queryHospitalList();
    }
}
