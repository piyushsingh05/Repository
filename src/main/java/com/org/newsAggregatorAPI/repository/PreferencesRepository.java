package com.org.newsAggregatorAPI.repository;

import com.org.newsAggregatorAPI.entity.Preferences;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreferencesRepository extends JpaRepository<Preferences,Long> {
}
