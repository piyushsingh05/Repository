package com.example.tasktracker.service;

import com.example.tasktracker.entity.Project;
import com.example.tasktracker.entity.User;
import com.example.tasktracker.repository.ProjectRepository;
import com.example.tasktracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    public Project createProject(Project project, List<Long> memberIds) {
        HashSet<User> members = new HashSet<>();
        if (memberIds != null) {
            for (Long id : memberIds) {
                userRepository.findById(id).ifPresent(members::add);
            }
        }
        project.setMembers(members);
        return projectRepository.save(project);
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Optional<Project> getProject(Long id) {
        return projectRepository.findById(id);
    }

    public void addMember(Long projectId, Long userId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        project.getMembers().add(user);
        projectRepository.save(project);
    }
}
