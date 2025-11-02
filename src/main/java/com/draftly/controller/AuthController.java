package com.draftly.controller;

import com.draftly.dto.AuthRequest;
import com.draftly.dto.AuthResponse;
import com.draftly.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    public AuthController(AuthService authService) { this.authService = authService; }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest req) {
        String token = authService.register(req.getEmail(), req.getPassword());
        return ResponseEntity.ok(new AuthResponse(token, req.getEmail()));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest req) {
        String token = authService.login(req.getEmail(), req.getPassword());
        return ResponseEntity.ok(new AuthResponse(token, req.getEmail()));
    }
}
