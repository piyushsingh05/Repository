package com.draftly.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "drafts")
public class Draft {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long emailId;

    @Lob
    private String aiText;

    @Lob
    private String finalText;

    @Enumerated(EnumType.STRING)
    private DraftStatus status = DraftStatus.GENERATED;

    private String tone;
    private Instant createdAt = Instant.now();
    private Instant updatedAt = Instant.now();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Long getEmailId() { return emailId; }
    public void setEmailId(Long emailId) { this.emailId = emailId; }
    public String getAiText() { return aiText; }
    public void setAiText(String aiText) { this.aiText = aiText; }
    public String getFinalText() { return finalText; }
    public void setFinalText(String finalText) { this.finalText = finalText; }
    public DraftStatus getStatus() { return status; }
    public void setStatus(DraftStatus status) { this.status = status; }
    public String getTone() { return tone; }
    public void setTone(String tone) { this.tone = tone; }
    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }
}
