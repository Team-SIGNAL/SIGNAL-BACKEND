package com.example.signalbackend.domain.coin.domain.repository;

import com.example.signalbackend.domain.coin.domain.Coin;
import com.example.signalbackend.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CoinRepository extends JpaRepository<Coin, UUID> {
    List<Coin> findAllByUserOrderByCreateDate(User user);
}
