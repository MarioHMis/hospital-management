package com.hospital.management.service;

import com.hospital.management.model.User;
import com.hospital.management.model.Role;
import com.hospital.management.repository.UserRepository;
import com.hospital.management.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public String register(String username, String password) {
        // Verificar si el usuario ya existe
        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("El usuario ya existe.");
        }

        User user = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .role(Role.USER) // Asegúrate que haya un rol USER en tu Enum Role
                .build();
        userRepository.save(user);
        return jwtService.generateToken(username);
    }

    public String authenticate(String username, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
        return jwtService.generateToken(username);
    }
}
