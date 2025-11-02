package com.draftly.service;

import com.draftly.model.User;
import com.draftly.repository.UserRepository;
import com.draftly.util.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public String register(String email, String password) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        User u = new User();
        u.setEmail(email);
        u.setPassword(passwordEncoder.encode(password));
        userRepository.save(u);
        return jwtUtil.generateToken(email);
    }

    public String login(String email, String password) {
        Optional<User> o = userRepository.findByEmail(email);
        if (o.isEmpty() || !passwordEncoder.matches(password, o.get().getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        return jwtUtil.generateToken(email);
    }
}
