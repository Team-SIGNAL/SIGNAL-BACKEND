package com.example.signalbackend.domain.admin.service;

import com.example.signalbackend.domain.admin.domain.Admin;
import com.example.signalbackend.domain.admin.domain.Role;
import com.example.signalbackend.domain.admin.domain.repository.AdminRepository;
import com.example.signalbackend.domain.admin.exception.HospitalAlreadyApprovedException;
import com.example.signalbackend.domain.admin.exception.AdminAlreadyException;
import com.example.signalbackend.domain.admin.exception.AdminNotFoundException;
import com.example.signalbackend.domain.admin.exception.HospitalVerificationProgressException;
import com.example.signalbackend.domain.admin.presentation.dto.request.UpdateHospitalImageRequest;
import com.example.signalbackend.domain.admin.presentation.dto.response.HospitalInfoResponse;
import com.example.signalbackend.global.exception.PasswordMixmatchException;
import com.example.signalbackend.domain.admin.facade.AdminFacade;
import com.example.signalbackend.domain.admin.presentation.dto.request.HospitalSignInRequest;
import com.example.signalbackend.domain.admin.presentation.dto.request.HospitalSignUpRequest;
import com.example.signalbackend.domain.admin.presentation.dto.response.AdminTokenResponse;
import com.example.signalbackend.global.security.jwt.JwtTokenProvider;
import com.example.signalbackend.global.utils.s3.S3Util;
import com.example.signalbackend.global.utils.token.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AdminService {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AdminFacade adminFacade;
    private final S3Util s3Util;

    @Transactional
    public void signup(HospitalSignUpRequest request) {
        boolean existAdmin = adminRepository.existsByAdminId(request.getAccountId());
        if(existAdmin) throw AdminAlreadyException.EXCEPTION;

        adminRepository.save(Admin.builder()
                .name(request.getName())
                .adminId(request.getAccountId())
                .phone(request.getPhone())
                .role(Role.NON_HOSPITAL)
                .password(passwordEncoder.encode(request.getPassword()))
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

    @Transactional
    public void updateHospitalImage(UpdateHospitalImageRequest request) {
         Admin admin = adminFacade.getCurrentAdmin();

         boolean isApproved = ((admin.getRole() == Role.HOSPITAL) && (admin.getHospitalImage() != null));
         if(isApproved) {
             throw HospitalAlreadyApprovedException.EXCEPTION;
         }

         boolean isNotAuthRequest = admin.getHospitalImage() == null;
         if(isNotAuthRequest) {
             admin.updateHospitalImage(request.getHospital_image());
         } else {
             throw HospitalVerificationProgressException.EXCEPTION;
         }
    }

    @Transactional(readOnly = true)
    public HospitalInfoResponse queryAdminInfo() {
        Admin admin = adminFacade.getCurrentAdmin();
        Boolean authRequestStatus = !((admin.getHospitalImage() == null) && (admin.getRole() == Role.NON_HOSPITAL));

        return new HospitalInfoResponse(
                admin.getName(),
                admin.getPhone(),
                admin.getProfile(),
                authRequestStatus
        );
    }
}
