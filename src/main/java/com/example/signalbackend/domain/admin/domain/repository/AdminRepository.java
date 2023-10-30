package com.example.signalbackend.domain.admin.domain.repository;

import com.example.signalbackend.domain.admin.domain.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends CrudRepository<Admin, Long> {
    Optional<Admin> findByAdminId(String adminId);

    boolean existsByAdminId(String adminId);
}
