package com.example.signalbackend.domain.user.service;

import com.example.signalbackend.domain.admin.domain.Role;
import com.example.signalbackend.global.exception.PasswordMixmatchException;
import com.example.signalbackend.domain.user.domain.User;
import com.example.signalbackend.domain.user.domain.repository.UserRepository;
import com.example.signalbackend.domain.user.exception.UserAlreadyException;
import com.example.signalbackend.domain.user.exception.UserNotFoundException;
import com.example.signalbackend.domain.user.facade.UserFacade;
import com.example.signalbackend.domain.user.presentation.request.UserSigninRequest;
import com.example.signalbackend.domain.user.presentation.request.UserSignupRequest;
import com.example.signalbackend.domain.user.presentation.response.UserInfoResponse;
import com.example.signalbackend.global.security.jwt.JwtTokenProvider;
import com.example.signalbackend.global.utils.token.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserFacade userFacade;

    @Transactional
    public void userSignup(UserSignupRequest request) {
        boolean existAccountId = userRepository.existsByAccountId(request.getAccountId());

        if(existAccountId) {
            throw UserAlreadyException.EXCEPTION;
        }

        userRepository.save(User.builder()
                        .name(request.getName())
                        .birth(request.getBirth())
                        .phone(request.getPhone())
                        .accountId(request.getAccountId())
                        .gender(request.getGender())
                        .password(passwordEncoder.encode(request.getPassword()))
                .build());
    }

    @Transactional
    public TokenResponse userSignin(UserSigninRequest request) {
        User user = userRepository.findByAccountId(request.getAccountId())
                .orElseThrow(()-> UserNotFoundException.EXCEPTION);

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw PasswordMixmatchException.EXCEPTION;
        }

        return jwtTokenProvider.generateToken(user.getAccountId(), Role.USER.toString());
    }

    @Transactional
    public void userSecession() {
        User user = userFacade.getCurrentUser();
        userRepository.deleteById(user.getId());
    }

    @Transactional(readOnly = true)
    public UserInfoResponse queryUserInfo() {
        User user = userFacade.getCurrentUser();

        return new UserInfoResponse(
                user.getName(),
                user.getPhone(),
                user.getBirth(),
                user.getProfile()
        );
    }
}
