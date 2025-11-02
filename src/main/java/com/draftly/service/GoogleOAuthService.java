package com.draftly.service;

import com.draftly.model.User;
import com.draftly.repository.UserRepository;
import com.draftly.util.CryptoUtil;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class GoogleOAuthService {

    private final UserRepository userRepository;

    @Value("${google.oauth2.client.id}")
    private String clientId;
    @Value("${google.oauth2.client.secret}")
    private String clientSecret;
    @Value("${google.oauth2.client.redirect-uri}")
    private String redirectUri;

    private static final JacksonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    public GoogleOAuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String buildAuthorizationUrl() {
        GoogleClientSecrets clientSecrets = new GoogleClientSecrets()
                .setInstalled(new GoogleClientSecrets.Details()
                        .setClientId(clientId)
                        .setClientSecret(clientSecret));

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                new NetHttpTransport(),
                JSON_FACTORY,
                clientSecrets,
                List.of("https://www.googleapis.com/auth/gmail.readonly",
                        "https://www.googleapis.com/auth/gmail.send"))
                .setAccessType("offline")
                .build();

        return flow.newAuthorizationUrl().setRedirectUri(redirectUri).build();
    }

    public void handleCallback(String code, String userEmail) throws IOException {
        GoogleTokenResponse tokenResponse = new GoogleAuthorizationCodeTokenRequest(
                new NetHttpTransport(),
                JSON_FACTORY,
                clientId,
                clientSecret,
                code,
                redirectUri)
                .execute();

        String accessToken = tokenResponse.getAccessToken();
        String refreshToken = tokenResponse.getRefreshToken();

        Optional<User> userOpt = userRepository.findByEmail(userEmail);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            try {
                user.setGoogleAccessToken(CryptoUtil.encrypt(accessToken));
                user.setGoogleRefreshToken(CryptoUtil.encrypt(refreshToken));
            } catch (Exception e) {
                e.printStackTrace();
                user.setGoogleAccessToken(accessToken);
                user.setGoogleRefreshToken(refreshToken);
            }
            userRepository.save(user);
        }
    }
}
