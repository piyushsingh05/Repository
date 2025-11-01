package com.example.tasktracker.service;

import com.example.tasktracker.entity.Notification;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NotificationService {

    // In-memory storage: userId -> notifications
    private final Map<Long, List<Notification>> userNotifications = new HashMap<>();

    // Add notification for a user
    public void addNotification(Long userId, String message) {
        userNotifications.computeIfAbsent(userId, k -> new ArrayList<>())
                .add(new Notification(message));
    }

    // Get all notifications for a user
    public List<Notification> getNotifications(Long userId) {
        return userNotifications.getOrDefault(userId, new ArrayList<>());
    }

    // Clear notifications (optional)
    public void clearNotifications(Long userId) {
        userNotifications.remove(userId);
    }
}
