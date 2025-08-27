package com.org.newsAggregatorAPI.repository;

import com.org.newsAggregatorAPI.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Long, User> {
}
