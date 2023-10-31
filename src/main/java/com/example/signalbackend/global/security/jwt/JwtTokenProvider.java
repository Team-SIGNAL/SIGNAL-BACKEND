package com.example.signalbackend.global.security.jwt;

import com.example.signalbackend.domain.admin.domain.Role;
import com.example.signalbackend.domain.refreshtoken.RefreshToken;
import com.example.signalbackend.domain.refreshtoken.repository.RefreshTokenRepository;
import com.example.signalbackend.global.exception.ExpiredTokenException;
import com.example.signalbackend.global.exception.InvalidTokenException;
import com.example.signalbackend.global.security.auth.AdminDetailsService;
import com.example.signalbackend.global.security.auth.AuthDetails;
import com.example.signalbackend.global.security.auth.AuthDetailsService;
import com.example.signalbackend.global.utils.token.dto.TokenResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    private final RefreshTokenRepository refreshTokenRepository;
    private final AuthDetailsService authDetailsService;
    private final AdminDetailsService adminDetailsService;

    @Value("${spring.jwt.secret-key}")
    private String secretKey;
    @Value("${spring.jwt.header}")
    private String header;
    @Value("${spring.jwt.prefix}")
    private String prefix;
    @Value("${spring.jwt.access-exp}")
    private Long accessExp;
    @Value("${spring.jwt.refresh-exp}")
    private Long refreshExp;

    private static final String ACCESS_KEY = "access_token";
    private static final String REFRESH_KEY = "refresh_token";

    public TokenResponse generateToken(String accountId, String role) {
        String accessToken = generateToken(accountId, role, ACCESS_KEY, accessExp);
        LocalDateTime accessExpiredAt = LocalDateTime.now().withNano(0).plusSeconds(accessExp);
        String refreshToken = generateToken(accountId, role, REFRESH_KEY, refreshExp);
        LocalDateTime refreshExpiredAt = LocalDateTime.now().withNano(0).plusSeconds(refreshExp);

        refreshTokenRepository.save(RefreshToken.builder()
                .id(accountId)
                .refreshToken(refreshToken)
                .build());

        return new TokenResponse(accessToken, refreshToken, accessExpiredAt, refreshExpiredAt);
    }

    private String generateToken(String id, String role, String type, Long exp) {
        return Jwts.builder()
                .setSubject(id)
                .setHeaderParam("typ", type)
                .claim("role", role)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .setExpiration(new Date(System.currentTimeMillis() + exp * 1000))
                .setIssuedAt(new Date())
                .compact();
    }

    public String resolveToken(HttpServletRequest request) {
        String bearer = request.getHeader(header);
        if (bearer != null && bearer.startsWith(prefix) && bearer.length() > prefix.length() + 1)
            return bearer.substring(prefix.length() + 1);
        return null;
    }

    public Authentication authentication(String token) {
        Claims body = getJws(token).getBody();

        boolean isNotRefreshToken = REFRESH_KEY.equals(getJws(token).getHeader().get("typ").toString());

        if (!isNotRefreshToken) throw InvalidTokenException.EXCEPTION;

        UserDetails userDetails = getDetails(body);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getRole(String token) {
        return getJws(token).getBody().get("role").toString();
    }

    private Jws<Claims> getJws(String token) {
        try {
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
        } catch (ExpiredJwtException e) {
            throw ExpiredTokenException.EXCEPTION;
        } catch (Exception e) {
            throw InvalidTokenException.EXCEPTION;
        }
    }

    private UserDetails getDetails(Claims body) {
        if (Role.USER.toString().equals(body.get("role").toString())) {
            return authDetailsService.loadUserByUsername(body.getSubject());
        } else {
            return adminDetailsService.loadUserByUsername(body.getSubject());
        }
    }
}
