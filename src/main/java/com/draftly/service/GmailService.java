package com.draftly.service;

import com.draftly.model.EmailEntity;
import com.draftly.model.User;
import com.draftly.repository.EmailRepository;
import com.draftly.repository.UserRepository;
import com.draftly.util.CryptoUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class GmailService {

    private final EmailRepository emailRepository;
    private final UserRepository userRepository;

    @Value("${google.oauth2.client.id:demo-client-id}")
    private String clientId;

    @Value("${google.oauth2.client.secret:demo-client-secret}")
    private String clientSecret;

    public GmailService(EmailRepository emailRepository, UserRepository userRepository) {
        this.emailRepository = emailRepository;
        this.userRepository = userRepository;
    }

    /**
     * ✅ Fetch mock emails for demo or fallback mode
     */
    public List<EmailEntity> fetchMockEmails(String userEmail, int limit) {
        List<EmailEntity> list = new ArrayList<>();
        for (int i = 1; i <= limit; i++) {
            EmailEntity e = new EmailEntity();
            e.setGmailId("mock-" + System.currentTimeMillis() + "-" + i);
            e.setSender("sender" + i + "@example.com");
            e.setSubject("Mock Subject " + i);
            e.setBody("Hello " + userEmail + ",\n\nThis is email " + i + " generated for demo purposes.");
            e.setThreadId("thread-" + i);
            e.setReceivedAt(Instant.now());
            emailRepository.save(e);
            list.add(e);
        }
        System.out.println("[Mock Gmail] Generated " + limit + " emails for " + userEmail);
        return list;
    }

    /**
     * ✅ Simulated Gmail fetch using Google credentials placeholder.
     * This avoids dependency on Gmail API client JAR.
     */
    public List<EmailEntity> fetchEmailsFromGmail(User user) throws Exception {
        System.out.println("[Simulated Gmail Fetch] for user: " + user.getEmail());

        // Decrypt tokens if available (for realism)
        String accessToken = user.getGoogleAccessToken() != null ?
                CryptoUtil.decrypt(user.getGoogleAccessToken()) : "mock-access-token";
        String refreshToken = user.getGoogleRefreshToken() != null ?
                CryptoUtil.decrypt(user.getGoogleRefreshToken()) : "mock-refresh-token";

        System.out.println("[Tokens] Access: " + accessToken.substring(0, 5) + "..., Refresh: " + refreshToken.substring(0, 5) + "...");

        // Simulate 3 recent emails
        List<EmailEntity> emails = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            EmailEntity e = new EmailEntity();
            e.setGmailId("sim-" + System.currentTimeMillis() + "-" + i);
            e.setSender("contact" + i + "@example.com");
            e.setSubject("Demo Subject " + i);
            e.setBody("Hi " + user.getEmail() + ",\n\nThis is a simulated Gmail message " + i + " for demo purposes.");
            e.setThreadId("thread-" + i);
            e.setReceivedAt(Instant.now());
            emailRepository.save(e);
            emails.add(e);
        }

        System.out.println("[Simulated Gmail] Fetched " + emails.size() + " messages.");
        return emails;
    }

    /**
     * ✅ Simulated Gmail send for demo
     */
    public boolean sendMail(String userEmail, String to, String subject, String body, String threadId) {
        System.out.println("[Simulated Gmail Send]");
        System.out.println("From: " + userEmail);
        System.out.println("To: " + to);
        System.out.println("Subject: " + subject);
        System.out.println("Body: " + body);
        System.out.println("Thread ID: " + threadId);
        return true;
    }
}
