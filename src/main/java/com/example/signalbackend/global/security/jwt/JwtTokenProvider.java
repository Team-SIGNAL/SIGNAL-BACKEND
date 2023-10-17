package com.example.signalbackend.global.security.jwt;

import com.example.signalbackend.domain.refresh.domain.RefreshToken;
import com.example.signalbackend.domain.user.domain.repository.RefreshTokenRepository;
import com.example.signalbackend.domain.user.presentation.dto.response.TokenResponse;
import com.example.signalbackend.global.exception.ExpiredJwtException;
import com.example.signalbackend.global.exception.InvalidJwtException;
import com.example.signalbackend.global.security.auth.AuthDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    private final RefreshTokenRepository refreshTokenRepository;
    private final AuthDetailsService authDetailsService;

    @Value("${spring.jwt.secret-key}")
    private String secretKey;
    @Value("${spring.jwt.refresh-exp}")
    private Long refresh;
    @Value("${spring.jwt.header}")
    private String header;
    @Value("${spring.jwt.prefix}")
    private String prefix;

    public String generateToken(String accountId , String type, Long expiration) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .setSubject(accountId)
                .setHeaderParam("type", type)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .compact();
    }

    public String generateRefreshToken(String accountId) {
        String refreshToken = generateToken(accountId, "refresh_token", refresh);

        refreshTokenRepository.save(
                RefreshToken.builder()
                        .accountId(accountId)
                        .token(refreshToken)
                        .build()
        );

        return refreshToken;
    }

    public String resolveToken(HttpServletRequest request) {
        String bearer = request.getHeader(header);
        return parseToken(bearer);
    }

    public String parseToken(String bearerToken) {
        if (bearerToken != null && bearerToken.startsWith(prefix)) {
            return bearerToken.replace(prefix, "");
        }
        return null;
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = authDetailsService.loadUserByUsername(getTokenSubject(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private String getTokenSubject(String token) {
        return getTokenBody(token).getSubject();
    }

    private Claims getTokenBody(String token) {
        try {
            return Jwts.parser().setSigningKey(secretKey)
                    .parseClaimsJws(token).getBody();
        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            throw ExpiredJwtException.EXCEPTION;
        } catch (Exception e) {
            throw InvalidJwtException.EXCEPTION;
        }
    }

}
