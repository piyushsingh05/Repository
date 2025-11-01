package com.example.tasktracker.controller;

import com.example.tasktracker.entity.Task;
import com.example.tasktracker.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public Task createTask(@RequestBody Task task,
                           @RequestParam Long projectId,
                           @RequestParam(required = false) Long assigneeId) {
        return taskService.createTask(task, projectId, assigneeId);
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/assignee/{userId}")
    public List<Task> getTasksByAssignee(@PathVariable Long userId) {
        return taskService.getTasksByAssignee(userId);
    }

    @PutMapping("/{taskId}/assign/{userId}")
    public Task assignTask(@PathVariable Long taskId, @PathVariable Long userId) {
        return taskService.assignTask(taskId, userId);
    }

    @PutMapping("/{taskId}")
    public Task updateTask(@PathVariable Long taskId, @RequestBody Task task) {
        task.setId(taskId);
        return taskService.updateTask(task);
    }

    @DeleteMapping("/{taskId}")
    public String deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return "Task deleted successfully!";
    }

    @PutMapping("/{taskId}/complete")
    public Task completeTask(@PathVariable Long taskId) {
        return taskService.markTaskAsCompleted(taskId);
    }

    @GetMapping("/filter")
    public List<Task> filterTasks(
            @RequestParam(required = false) Long assigneeId,
            @RequestParam(required = false) Long projectId,
            @RequestParam(required = false) Boolean completed
    ) {
        return taskService.filterTasks(assigneeId, projectId, completed);
    }

    @GetMapping("/search")
    public List<Task> searchTasks(@RequestParam String keyword) {
        return taskService.searchTasks(keyword);
    }
}
