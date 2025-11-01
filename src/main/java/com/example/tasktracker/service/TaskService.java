package com.example.tasktracker.service;

import com.example.tasktracker.entity.Project;
import com.example.tasktracker.entity.Task;
import com.example.tasktracker.entity.User;
import com.example.tasktracker.repository.ProjectRepository;
import com.example.tasktracker.repository.TaskRepository;
import com.example.tasktracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private NotificationService notificationService;

    public Task createTask(Task task, Long projectId, Long assigneeId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        task.setProject(project);

        if (assigneeId != null) {
            User user = userRepository.findById(assigneeId)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            task.setAssignee(user);
        }

        return taskRepository.save(task);
    }

    public List<Task> getTasksByAssignee(Long userId) {
        return taskRepository.findByAssigneeId(userId);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task updateTask(Task task) {
        return taskRepository.save(task);
    }

    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    public Task assignTask(Long taskId, Long userId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        task.setAssignee(user);
        Task updatedTask = taskRepository.save(task);

        String message = "Task '" + task.getTitle() + "' has been assigned to you";
        notificationService.addNotification(user.getId(), message);

        System.out.println("Notification: " + message);
        return updatedTask;
    }

    public Task markTaskAsCompleted(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        task.setCompleted(true);
        Task updatedTask = taskRepository.save(task);

        if (task.getAssignee() != null) {
            String message = "Task '" + task.getTitle() + "' completed by " + task.getAssignee().getUsername();
            notificationService.addNotification(task.getAssignee().getId(), message);
            System.out.println("Notification: " + message);
        } else {
            System.out.println("Notification: Task '" + task.getTitle() + "' completed.");
        }
        return updatedTask;
    }

    public List<Task> filterTasks(Long assigneeId, Long projectId, Boolean completed) {
        return taskRepository.filterTasks(assigneeId, projectId, completed);
    }

    public List<Task> searchTasks(String keyword) {
        return taskRepository.searchByKeyword(keyword);
    }
}
