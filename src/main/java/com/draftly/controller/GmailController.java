package com.draftly.controller;

import com.draftly.model.EmailEntity;
import com.draftly.model.User;
import com.draftly.repository.UserRepository;
import com.draftly.service.GmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gmail")
public class GmailController {
    private final GmailService gmailService;
    private final UserRepository userRepository;

    public GmailController(GmailService gmailService, UserRepository userRepository) { this.gmailService = gmailService; this.userRepository = userRepository; }

    @GetMapping("/fetch")
    public ResponseEntity<List<EmailEntity>> fetch(@RequestParam(defaultValue = "3") int limit, Authentication auth) {
        String userEmail = auth.getName();
        User user = userRepository.findByEmail(userEmail).get();
        try {
            List<EmailEntity> emails = gmailService.fetchEmailsFromGmail(user);
            return ResponseEntity.ok(emails);
        } catch (Exception e) {
            // fallback to mock
            List<EmailEntity> emails = gmailService.fetchMockEmails(userEmail, limit);
            return ResponseEntity.ok(emails);
        }
    }

    @PostMapping("/send")
    public ResponseEntity<String> send(@RequestParam String to,
                                       @RequestParam String subject,
                                       @RequestBody String body,
                                       @RequestParam(required = false) String threadId,
                                       Authentication auth) {
        String userEmail = auth.getName();
        boolean ok = gmailService.sendMail(userEmail, to, subject, body, threadId);
        return ok ? ResponseEntity.ok("sent") : ResponseEntity.status(500).body("failed");
    }
}
