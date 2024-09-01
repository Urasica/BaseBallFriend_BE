package com.demo.project.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "fhsjkajhfawioidlajdskalfjsafoliwjlawf"; // 상수로 선언된 비밀 키

    // SecretKey 생성
    private static SecretKey getSecretKey() {
        return new SecretKeySpec(SECRET_KEY.getBytes(), SignatureAlgorithm.HS256.getJcaName());
    }

    // JWT 토큰 생성
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1일 만료
                .signWith(getSecretKey()) // 최신 사용법
                .compact();
    }

    // JWT 토큰에서 클레임 추출
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSecretKey()) // 최신 사용법
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // JWT 토큰에서 사용자 이름 추출
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    // 토큰 만료 여부 확인
    public boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    // 토큰 유효성 검사
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}