package com.example.signalbackend.domain.user.facade;

import com.example.signalbackend.domain.user.domain.User;
import com.example.signalbackend.domain.user.domain.repository.UserRepository;
import com.example.signalbackend.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class UserFacade{

    private final UserRepository userRepository;

    public User getUserByAccountId(String accountId) {
        return userRepository.findByAccountId(accountId).orElseThrow(()->UserNotFoundException.EXCEPTION);
    }

    public User getCurrentUser() {
        String accountId = SecurityContextHolder.getContext().getAuthentication().getName();
        return getUserByAccountId(accountId);
    }

    public Optional<User> getUserById(UUID id) {
        return userRepository.findById(id);
    }
}
