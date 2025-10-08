package com.org.newsAggregatorAPI.controller;

import com.org.newsAggregatorAPI.DTO.AuthRequest;
import com.org.newsAggregatorAPI.DTO.AuthResponse;
import com.org.newsAggregatorAPI.DTO.UserDTO;
import com.org.newsAggregatorAPI.entity.Preferences;
import com.org.newsAggregatorAPI.entity.User;
import com.org.newsAggregatorAPI.service.NewsAggregatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class NewsAggregatorController {
    @Autowired
    private NewsAggregatorService _newsAggregatorService;

    public NewsAggregatorController(NewsAggregatorService _newsAggregatorService) {
        this._newsAggregatorService = _newsAggregatorService;
    }

    public NewsAggregatorService get_newsAggregatorService() {
        return _newsAggregatorService;
    }

    public void set_newsAggregatorService(NewsAggregatorService _newsAggregatorService) {
        this._newsAggregatorService = _newsAggregatorService;
    }

    @PostMapping("/register")
    public User register(@RequestBody UserDTO userDTO){
        return _newsAggregatorService.registerUser(userDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request){
        String token =  _newsAggregatorService.loginUser(request.getUsername(), request.getPassword());
        return ResponseEntity.ok(new AuthResponse(token));
    }

//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestParam("username") String username , @RequestParam("password") String password){
//        String token =  _newsAggregatorService.loginUser(username,password);
//        return ResponseEntity.ok(token);
//    }


    @GetMapping("/preferences")
    public Preferences retriveNewsPrefrences(Authentication authentication){
        String userName = authentication.getName();
        return _newsAggregatorService.retriveNewsPrefrences(userName);
    }

    @PostMapping("/preferences")
    public Preferences updateNewsPrefrences(Authentication authentication , @RequestBody Preferences newPreferences){
        String userName =authentication.getName();
        return _newsAggregatorService.updateNewsPrefrences(userName, newPreferences);
    }

    @GetMapping("/tokenHey")
    public String hey() {
        return "Hey";
    }

}
