package com.innowise.apigateway.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class InternalTokenProvider {
    private final SecretKey key;

    public InternalTokenProvider(@Value("${jwt.secret}") String secret) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String adminBearer() {
        String token = Jwts.builder()
                .subject("internal-gateway")
                .claim("userId", 0L)
                .claim("role", "ADMIN")
                .claim("type", "access")
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 60_000))
                .signWith(key)
                .compact();
        return "Bearer " + token;
    }
}
