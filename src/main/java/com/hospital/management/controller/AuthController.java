package com.hospital.management.controller;

import com.hospital.management.dto.AuthRequest;
import com.hospital.management.security.JwtService;
import com.hospital.management.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.register(request.getUsername(), request.getPassword()));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest request) {
        String token = jwtService.generateToken(request.getUsername());
        return ResponseEntity.ok(token);
    }
}
