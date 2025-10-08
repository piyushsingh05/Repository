package com.org.newsAggregatorAPI.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "preferences")
public class Preferences {
    @Id
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "users")
    private User user;

    private String categories;

    private String sources;

    public Preferences(String categories, Long id, String sources, User user) {
        this.categories = categories;
        this.id = id;
        this.sources = sources;
        this.user = user;
    }

    public Preferences(){

    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSources() {
        return sources;
    }

    public void setSources(String sources) {
        this.sources = sources;
    }
}
