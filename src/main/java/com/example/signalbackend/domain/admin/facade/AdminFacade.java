package com.example.signalbackend.domain.admin.facade;

import com.example.signalbackend.domain.admin.domain.Admin;
import com.example.signalbackend.domain.admin.domain.repository.AdminRepository;
import com.example.signalbackend.domain.admin.exception.AdminNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class AdminFacade {

    private final AdminRepository adminRepository;

    public Admin getAdminByAdminId(String adminId) {
        return adminRepository.findByAdminId(adminId).orElseThrow(() -> AdminNotFoundException.EXCEPTION);
    }

    public Admin getCurrentAdmin() {
        String adminId = SecurityContextHolder.getContext().getAuthentication().getName();
        return getAdminByAdminId(adminId);
    }

    public Optional<Admin> getAdminById(UUID id) {
        return adminRepository.findById(id);
    }
}
