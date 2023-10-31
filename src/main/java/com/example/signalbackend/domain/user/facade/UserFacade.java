package com.example.signalbackend.domain.user.facade;

import com.example.signalbackend.domain.user.domain.User;
import com.example.signalbackend.domain.user.domain.repository.UserRepository;
import com.example.signalbackend.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserFacade{

    private final UserRepository userRepository;

    public User getUserByAccountId(String accountId) {
        return userRepository.findByAccountId(accountId).orElseThrow(()->UserNotFoundException.EXCEPTION);
    }
}