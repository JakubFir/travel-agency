package com.example.travelagency.service;

import com.example.travelagency.config.SecurityConfig;
import com.example.travelagency.model.persistence.User;
import com.example.travelagency.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class JwtService {

    private final SecurityConfig securityConfig;
    private final UserRepository userRepository;

    public String generateToken(String username) {
        return generateToken(new HashMap<>(), username);
    }

    public String generateToken(Map<String, Object> extraClaims, String username) {
        User user = userRepository.findUserByUsername(username).orElseThrow();
        extraClaims.put("role", user.getRole());
        extraClaims.put("userId", user.getId());
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 10000 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public boolean isTokenValid(String token, String subject) {
        final String username = extractUsername(token);
        return (username.equals(subject)) && !isTokenExpired(token);

    }

    private boolean isTokenExpired(String token) {
        Date today = Date.from(Instant.now());
        return extractAllClaims(token).getExpiration().before(today);
    }

    public Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(securityConfig.getSecretKey());
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
