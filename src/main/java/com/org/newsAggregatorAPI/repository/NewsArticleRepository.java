package com.org.newsAggregatorAPI.repository;

import com.org.newsAggregatorAPI.entity.NewsArticle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsArticleRepository extends JpaRepository<NewsArticle,Long> {
}
