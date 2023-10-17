package com.example.signalbackend.domain.user.domain.repository;

import com.example.signalbackend.domain.refresh.domain.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {

}
