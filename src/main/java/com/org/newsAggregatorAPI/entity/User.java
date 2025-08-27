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


}
