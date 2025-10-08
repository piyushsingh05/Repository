package com.org.newsAggregatorAPI.controller;

import com.org.newsAggregatorAPI.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/news")
public class NewsController {
    @Autowired
    private final NewsService _newsService;

    public NewsController(NewsService newsService) {
        _newsService = newsService;
    }
}
