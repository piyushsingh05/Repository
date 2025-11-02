package com.draftly.controller;

import com.draftly.dto.DraftRequest;
import com.draftly.dto.DraftResponse;
import com.draftly.model.Draft;
import com.draftly.model.DraftStatus;
import com.draftly.repository.UserRepository;
import com.draftly.service.AIService;
import com.draftly.service.DraftService;
import com.draftly.service.GmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/drafts")
public class DraftController {
    private final AIService aiService;
    private final DraftService draftService;
    private final GmailService gmailService;
    private final UserRepository userRepository;

    public DraftController(AIService aiService, DraftService draftService, GmailService gmailService, UserRepository userRepository) {
        this.aiService = aiService;
        this.draftService = draftService;
        this.gmailService = gmailService;
        this.userRepository = userRepository;
    }

    @PostMapping("/generate")
    public ResponseEntity<DraftResponse> generate(@RequestBody DraftRequest req, Authentication auth) {
        String userEmail = auth.getName();
        Long userId = userRepository.findByEmail(userEmail).get().getId();

        String aiText = aiService.generateReply(req.getSender(), req.getSubject(), req.getBody(), req.getTone());
        Draft d = draftService.createDraft(userId, req, aiText);
        DraftResponse res = new DraftResponse(d.getId(), d.getAiText(), d.getStatus().name());
        return ResponseEntity.ok(res);
    }

    @GetMapping
    public ResponseEntity<List<DraftResponse>> list(Authentication auth) {
        String userEmail = auth.getName();
        Long userId = userRepository.findByEmail(userEmail).get().getId();
        List<Draft> drafts = draftService.listForUser(userId);
        List<DraftResponse> out = drafts.stream().map(d -> new DraftResponse(d.getId(), d.getAiText(), d.getStatus().name())).collect(Collectors.toList());
        return ResponseEntity.ok(out);
    }

    @PostMapping("/{id}/approve")
    public ResponseEntity<String> approve(@PathVariable Long id, Authentication auth) {
        Optional<Draft> o = draftService.findById(id);
        if (o.isEmpty()) return ResponseEntity.notFound().build();
        Draft d = o.get();
        draftService.updateFinalText(d, d.getFinalText(), DraftStatus.APPROVED);
        boolean sent = gmailService.sendMail(auth.getName(), "recipient@example.com", "Re: " + d.getId(), d.getFinalText(), null);
        if (sent) {
            draftService.updateFinalText(d, d.getFinalText(), DraftStatus.SENT);
            return ResponseEntity.ok("sent");
        } else {
            draftService.updateFinalText(d, d.getFinalText(), DraftStatus.FAILED);
            return ResponseEntity.status(500).body("failed");
        }
    }

    @PostMapping("/{id}/edit")
    public ResponseEntity<DraftResponse> edit(@PathVariable Long id, @RequestBody String newText) {
        Optional<Draft> o = draftService.findById(id);
        if (o.isEmpty()) return ResponseEntity.notFound().build();
        Draft d = o.get();
        Draft updated = draftService.updateFinalText(d, newText, DraftStatus.EDITED);
        return ResponseEntity.ok(new DraftResponse(updated.getId(), updated.getFinalText(), updated.getStatus().name()));
    }
}
