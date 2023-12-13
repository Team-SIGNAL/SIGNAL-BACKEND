package com.example.signalbackend.domain.user.domain.repository;

import com.example.signalbackend.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByAccountId(String accountId);
    boolean existsByAccountId(String accountId);
}
