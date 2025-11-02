package com.draftly.dto;

public class DraftResponse {
    private Long draftId;
    private String aiText;
    private String status;
    public DraftResponse() {}
    public DraftResponse(Long draftId, String aiText, String status) {
        this.draftId = draftId; this.aiText = aiText; this.status = status;
    }
    public Long getDraftId() { return draftId; }
    public void setDraftId(Long draftId) { this.draftId = draftId; }
    public String getAiText() { return aiText; }
    public void setAiText(String aiText) { this.aiText = aiText; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
