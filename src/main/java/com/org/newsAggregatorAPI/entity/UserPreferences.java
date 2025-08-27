package com.org.newsAggregatorAPI.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "preferences")
public class UserPreferences {
    @Id
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "users")
    private User user;

    private String categories;

    private String sources;
}
