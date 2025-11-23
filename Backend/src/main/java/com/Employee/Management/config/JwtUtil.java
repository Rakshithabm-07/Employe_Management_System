package com.Employee.Management.config;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.Employee.Management.entity.Employee;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    private final SecretKey key;
    private final long expirationMs;

    public JwtUtil(
            @Value("${app.jwt-secret}") String secret,
            @Value("${app.jwt-expiration-ms}") long expirationMs
    ) {
        byte[] keyBytes = secret.getBytes();
        byte[] finalKey = java.util.Arrays.copyOf(keyBytes, 32);
        this.key = Keys.hmacShaKeyFor(finalKey);
        this.expirationMs = expirationMs;
    }

    public String generateToken(Employee emp) {
        return Jwts.builder()
                .setSubject(emp.getId().toString())
                .claim("role", emp.getRole())
                .claim("employmentCode", emp.getEmploymentCode())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
