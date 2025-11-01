package com.example.tasktracker.controller;

import com.example.tasktracker.entity.Project;
import com.example.tasktracker.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping
    public Project createProject(@RequestBody Project project, @RequestParam(required = false) List<Long> memberIds) {
        return projectService.createProject(project, memberIds);
    }

    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/{id}")
    public Project getProject(@PathVariable Long id) {
        return projectService.getProject(id).orElseThrow(() -> new RuntimeException("Project not found"));
    }

    @PostMapping("/{projectId}/members/{userId}")
    public String addMember(@PathVariable Long projectId, @PathVariable Long userId) {
        projectService.addMember(projectId, userId);
        return "Member added successfully!";
    }
}
