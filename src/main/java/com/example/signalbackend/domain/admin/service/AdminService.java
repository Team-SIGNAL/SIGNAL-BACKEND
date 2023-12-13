package com.example.signalbackend.domain.admin.service;

import com.example.signalbackend.domain.admin.domain.Admin;
import com.example.signalbackend.domain.admin.domain.AuthStatus;
import com.example.signalbackend.domain.admin.domain.Role;
import com.example.signalbackend.domain.admin.domain.repository.AdminRepository;
import com.example.signalbackend.domain.admin.exception.AdminAlreadyException;
import com.example.signalbackend.domain.admin.exception.AdminNotFoundException;
import com.example.signalbackend.domain.admin.exception.HospitalAlreadyApprovedException;
import com.example.signalbackend.domain.admin.exception.HospitalVerificationProgressException;
import com.example.signalbackend.domain.admin.presentation.dto.request.UpdateHospitalAuthStatusRequest;
import com.example.signalbackend.domain.admin.presentation.dto.request.UpdateHospitalImageRequest;
import com.example.signalbackend.domain.admin.presentation.dto.response.HospitalAuthDetailsResponse;
import com.example.signalbackend.domain.admin.presentation.dto.response.HospitalAuthRequestElement;
import com.example.signalbackend.domain.admin.presentation.dto.response.HospitalAuthRequestListResponse;
import com.example.signalbackend.domain.admin.presentation.dto.response.HospitalInfoResponse;
import com.example.signalbackend.domain.admin.presentation.dto.response.HospitalListElement;
import com.example.signalbackend.domain.admin.presentation.dto.response.HospitalListResponse;
import com.example.signalbackend.global.exception.PasswordMixmatchException;
import com.example.signalbackend.domain.admin.facade.AdminFacade;
import com.example.signalbackend.domain.admin.presentation.dto.request.HospitalSignInRequest;
import com.example.signalbackend.domain.admin.presentation.dto.request.HospitalSignUpRequest;
import com.example.signalbackend.domain.admin.presentation.dto.response.AdminTokenResponse;
import com.example.signalbackend.global.security.jwt.JwtTokenProvider;
import com.example.signalbackend.global.utils.token.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AdminService {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AdminFacade adminFacade;

    @Transactional
    public void signup(HospitalSignUpRequest request) {
        boolean existAdmin = adminRepository.existsByAdminId(request.getAccountId());
        if(existAdmin) throw AdminAlreadyException.EXCEPTION;

        adminRepository.save(Admin.builder()
                .adminId(request.getAccountId())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .phone(request.getPhone())
                .role(Role.NON_HOSPITAL)
                .address(request.getAddress())
                .authStatus(AuthStatus.NON_AUTH)
                .build());
    }

    @Transactional
    public AdminTokenResponse signin(HospitalSignInRequest request) {
        Admin admin = adminRepository.findByAdminId(request.getAccountId())
                .orElseThrow(() -> AdminNotFoundException.EXCEPTION);

        if(!passwordEncoder.matches(request.getPassword(), admin.getPassword())) {
            throw PasswordMixmatchException.EXCEPTION;
        }

        TokenResponse token = jwtTokenProvider.generateToken(admin.getAdminId(), admin.getRole().toString());
        return new AdminTokenResponse(token.getAccessToken(), token.getRefreshToken(), token.getAccessExpiredAt(),
                token.getRefreshExpiredAt(), admin.getRole());
    }

    @Transactional(readOnly = true)
    public HospitalInfoResponse queryAdminInfo() {
        Admin admin = adminFacade.getCurrentAdmin();

        return new HospitalInfoResponse(
                admin.getName(),
                admin.getPhone(),
                admin.getProfile(),
                admin.getAuthStatus(),
                admin.getAddress(),
                admin.getHospitalImage()
        );

    }

    @Transactional
    public void updateHospitalImage(UpdateHospitalImageRequest request) {
        Admin admin = adminFacade.getCurrentAdmin();

        boolean isApproved = ((admin.getRole().equals(Role.HOSPITAL)) && (admin.getAuthStatus().equals(AuthStatus.VERIFIED)));
        System.out.println(isApproved);
        if(isApproved) {
            throw HospitalAlreadyApprovedException.EXCEPTION;
        }

        admin.updateHospitalImage(request.getHospital_image(), AuthStatus.WAIT);
    }

    @Transactional
    public void adminSecession() {
        Admin admin = adminFacade.getCurrentAdmin();
        adminRepository.deleteById(admin.getId());
    }

    @Transactional(readOnly = true)
    public HospitalAuthRequestListResponse queryHospitalAuthRequestList() {
        List<Admin> adminList = adminRepository.findAllByAuthStatusAndRole(AuthStatus.WAIT, Role.NON_HOSPITAL);

        List<HospitalAuthRequestElement> hospitalAuthRequestList = adminList.stream().map(admin -> {
            return buildHospitalAuth(admin);
        }).toList();

        return new HospitalAuthRequestListResponse(hospitalAuthRequestList);
    }

    private HospitalAuthRequestElement buildHospitalAuth(Admin admin) {
        return HospitalAuthRequestElement.builder()
                .id(admin.getId())
                .name(admin.getName())
                .phone(admin.getPhone())
                .build();
    }

    @Transactional(readOnly = true)
    public HospitalAuthDetailsResponse queryHospitalAuthDetails(UUID hospitalId) {
        Admin admin = adminRepository.findById(hospitalId).orElseThrow(()->AdminNotFoundException.EXCEPTION);

        return new HospitalAuthDetailsResponse(
                admin.getId(),
                admin.getName(),
                admin.getPhone(),
                admin.getAddress(),
                admin.getHospitalImage(),
                admin.getAdminId()
        );
    }

    @Transactional
    public void updateHospitalAuthStatus(UUID hospitalId, UpdateHospitalAuthStatusRequest request) {
        adminFacade.getCurrentAdmin();
        Admin admin = adminRepository.findById(hospitalId).orElseThrow(()->AdminNotFoundException.EXCEPTION);

        if(request.getAuthStatus().equals(AuthStatus.VERIFIED)) {
            admin.updateAuthStatusAndRole(request.getAuthStatus(), Role.HOSPITAL);
        } else {
            admin.updateAuthStatus(request.getAuthStatus());
        }
    }

    @Transactional(readOnly = true)
    public HospitalListResponse queryHospitalList() {
        List<HospitalListElement> hospitalList = adminRepository.findAllByRoleOrderByName(Role.HOSPITAL).stream()
                .map( admin -> {
                    return HospitalListElement.builder()
                            .id(admin.getId())
                            .profile(admin.getProfile())
                            .name(admin.getName())
                            .phone(admin.getPhone())
                            .address(admin.getAddress()).build();
                }).collect(Collectors.toList());

        return new HospitalListResponse(hospitalList);
    }
}
