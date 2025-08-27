package com.org.newsAggregatorAPI.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true)
    private String username;
    private String password;


    @ElementCollection
    private List<String> preferences;

    @ElementCollection
    private Set<String> readArticles = new HashSet<>();

    @ElementCollection
    private Set<String> favoriteArticles = new HashSet<>();

    public User(Long userId, String username, String password, List<String> preferences, Set<String> readArticles, Set<String> favoriteArticles) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.preferences = preferences;
        this.readArticles = readArticles;
        this.favoriteArticles = favoriteArticles;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getPreferences() {
        return preferences;
    }

    public void setPreferences(List<String> preferences) {
        this.preferences = preferences;
    }

    public Set<String> getReadArticles() {
        return readArticles;
    }

    public void setReadArticles(Set<String> readArticles) {
        this.readArticles = readArticles;
    }

    public Set<String> getFavoriteArticles() {
        return favoriteArticles;
    }

    public void setFavoriteArticles(Set<String> favoriteArticles) {
        this.favoriteArticles = favoriteArticles;
    }
}
