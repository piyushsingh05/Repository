package com.draftly.controller;

import com.draftly.service.GoogleOAuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/oauth/google")
public class GoogleOAuthController {

    private final GoogleOAuthService googleOAuthService;

    public GoogleOAuthController(GoogleOAuthService googleOAuthService) {
        this.googleOAuthService = googleOAuthService;
    }

    @GetMapping("/authorize")
    public ResponseEntity<String> authorize() {
        String url = googleOAuthService.buildAuthorizationUrl();
        return ResponseEntity.ok(url);
    }

    @GetMapping("/callback")
    public ResponseEntity<String> callback(@RequestParam("code") String code,
                                           Authentication auth) {
        try {
            String userEmail = auth.getName();
            googleOAuthService.handleCallback(code, userEmail);
            return ResponseEntity.ok("Google account connected successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("OAuth2 failed: " + e.getMessage());
        }
    }
}
