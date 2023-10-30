package com.example.signalbackend.domain.admin.service;

import com.example.signalbackend.domain.admin.domain.Admin;
import com.example.signalbackend.domain.admin.domain.Role;
import com.example.signalbackend.domain.admin.domain.repository.AdminRepository;
import com.example.signalbackend.domain.admin.exception.AdminAlreadyException;
import com.example.signalbackend.domain.admin.facade.AdminFacade;
import com.example.signalbackend.domain.admin.presentation.dto.request.AdminSignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AdminService {

    private final AdminFacade adminFacade;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signup(AdminSignUpRequest request) {
        boolean existAdmin = adminRepository.existsByAdminId(request.getAccountId());
        if(existAdmin) throw AdminAlreadyException.EXCEPTION;

        adminRepository.save(Admin.builder()
                .adminId(request.getAccountId())
                .phone(request.getPhone())
                .role(Role.HOSPITAL)
                .password(passwordEncoder.encode(request.getPassword()))
                .build());
    }
}
