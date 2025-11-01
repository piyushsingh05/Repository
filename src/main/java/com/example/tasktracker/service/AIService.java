package com.example.tasktracker.service;

import org.springframework.stereotype.Service;

@Service
public class AIService {

    // Simulate generating a task description from a short input
    public String generateTaskDescription(String prompt) {
        // For demo, we just create a formatted description
        return "Task Description for '" + prompt + "':\n" +
               "- This task involves working on " + prompt + ".\n" +
               "- Please complete it according to project requirements.\n" +
               "- Assign priority and deadline as needed.";
    }
}
