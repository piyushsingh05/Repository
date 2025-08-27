package com.org.newsAggregatorAPI.controller;

import com.org.newsAggregatorAPI.DTO.UserDTO;
import com.org.newsAggregatorAPI.entity.User;
import com.org.newsAggregatorAPI.service.NewsAggregatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class NewsAggregatorController {
    @Autowired
    private NewsAggregatorService _newsAggregatorService;

    @PostMapping("/register")
    public User register(@RequestBody UserDTO userDTO){
        User registeredUser =_newsAggregatorService.registerUser(userDTO);
        return registeredUser;
    }


}
