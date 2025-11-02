package com.draftly.dto;

public class DraftRequest {
    private Long emailId;
    private String sender;
    private String subject;
    private String body;
    private String tone;
    public Long getEmailId() { return emailId; }
    public void setEmailId(Long emailId) { this.emailId = emailId; }
    public String getSender() { return sender; }
    public void setSender(String sender) { this.sender = sender; }
    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }
    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }
    public String getTone() { return tone; }
    public void setTone(String tone) { this.tone = tone; }
}
