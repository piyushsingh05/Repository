package com.draftly.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;
    private String password;
    private String googleAccessToken;
    private String googleRefreshToken;
    private Instant createdAt = Instant.now();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getGoogleAccessToken() { return googleAccessToken; }
    public void setGoogleAccessToken(String googleAccessToken) { this.googleAccessToken = googleAccessToken; }
    public String getGoogleRefreshToken() { return googleRefreshToken; }
    public void setGoogleRefreshToken(String googleRefreshToken) { this.googleRefreshToken = googleRefreshToken; }
    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}
