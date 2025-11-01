package com.example.tasktracker.controller;

import com.example.tasktracker.entity.Notification;
import com.example.tasktracker.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/{userId}")
    public List<Notification> getNotifications(@PathVariable Long userId) {
        return notificationService.getNotifications(userId);
    }

    @DeleteMapping("/{userId}")
    public String clearNotifications(@PathVariable Long userId) {
        notificationService.clearNotifications(userId);
        return "Notifications cleared for user " + userId;
    }
}
