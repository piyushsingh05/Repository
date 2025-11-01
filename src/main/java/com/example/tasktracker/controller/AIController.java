package com.example.tasktracker.controller;

import com.example.tasktracker.service.AIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
public class AIController {

    @Autowired
    private AIService aiService;

    @PostMapping("/generate")
    public String generateDescription(@RequestParam String prompt) {
        return aiService.generateTaskDescription(prompt);
    }
}
