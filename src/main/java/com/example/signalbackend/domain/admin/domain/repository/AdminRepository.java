package com.example.signalbackend.domain.admin.domain.repository;

import com.example.signalbackend.domain.admin.domain.Admin;
import com.example.signalbackend.domain.admin.domain.AuthStatus;
import com.example.signalbackend.domain.admin.domain.Role;
import com.example.signalbackend.domain.admin.presentation.dto.response.HospitalAuthRequestElement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AdminRepository extends JpaRepository<Admin, UUID> {
    Optional<Admin> findByAdminId(String adminId);
    boolean existsByAdminId(String adminId);
    List<Admin> findAllByAuthStatusAndRole(AuthStatus authStatus, Role role);
    List<Admin> findAllByRoleOrderByName(Role role);
}
