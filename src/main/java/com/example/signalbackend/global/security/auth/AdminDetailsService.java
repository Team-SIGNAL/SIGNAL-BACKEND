package com.example.signalbackend.global.security.auth;

import com.example.signalbackend.domain.admin.domain.Admin;
import com.example.signalbackend.domain.admin.facade.AdminFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdminDetailsService implements UserDetailsService {

    private final AdminFacade adminFacade;

    @Override
    public UserDetails loadUserByUsername(String accountId) throws UsernameNotFoundException {
        Admin admin = adminFacade.getAdminByAdminId(accountId);
        return new AdminDetails(admin.getAdminId(), admin.getRole());
    }
}
