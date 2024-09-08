package com.demo.project.user.controller;

import com.demo.project.jwt.JwtResponse;
import com.demo.project.jwt.JwtUtil;
import com.demo.project.user.User;
import com.demo.project.user.dto.UserDto;
import com.demo.project.user.dto.LoginRequest;
import com.demo.project.user.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserDto userDto) {
        // 이미 존재하는 사용자 이름 확인
        if (userRepository.findByUsername(userDto.getUsername()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists.");
        }

        try {
            // 사용자 생성 및 설정
            User user = new User();
            user.setUsername(userDto.getUsername());
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user.setName(userDto.getName());
            user.setPhoneNumber(userDto.getPhoneNumber());

            // 사용자 저장
            userRepository.save(user);
            return ResponseEntity.ok("{\"message\":\"User registered successfully\"}");

        } catch (Exception e) {
            // 데이터베이스 저장 실패 등의 예외 처리
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User registration failed.");
        }
    }

    @PostMapping("/login")
    public JwtResponse login(@RequestBody @Valid LoginRequest loginRequest) {
        Optional<User> userOpt = userRepository.findByUsername(loginRequest.getUsername());

        if (userOpt.isEmpty() || !passwordEncoder.matches(loginRequest.getPassword(), userOpt.get().getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        User user = userOpt.get();
        String token = jwtUtil.generateToken(user);
        return new JwtResponse(token);
    }

    @GetMapping("/check-token")
    public ResponseEntity<String>  protectedEndpoint(@RequestHeader("Authorization") String token) {
        // Bearer 제거
        token = token.replace("Bearer ", "");

        // 토큰에서 사용자 정보 추출
        String username = jwtUtil.extractUsername(token);

        // 사용자 정보 조회
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isEmpty() || !jwtUtil.validateToken(token, userOpt.get())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is expired.");
        }

        return ResponseEntity.ok("Token is valid.");
    }
}