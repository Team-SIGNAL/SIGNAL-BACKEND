package com.example.signalbackend.domain.refreshtoken.repository;

import com.example.signalbackend.domain.refreshtoken.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
}
