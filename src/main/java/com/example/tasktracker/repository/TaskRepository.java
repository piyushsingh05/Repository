package com.example.tasktracker.repository;

import com.example.tasktracker.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByAssigneeId(Long assigneeId);
    List<Task> findByProjectId(Long projectId);
    List<Task> findByCompleted(Boolean completed);

    @Query("SELECT t FROM Task t WHERE LOWER(t.title) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
           "OR LOWER(t.description) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Task> searchByKeyword(@Param("keyword") String keyword);

    @Query("SELECT t FROM Task t WHERE (:assigneeId IS NULL OR t.assignee.id = :assigneeId) " +
           "AND (:projectId IS NULL OR t.project.id = :projectId) " +
           "AND (:completed IS NULL OR t.completed = :completed)")
    List<Task> filterTasks(@Param("assigneeId") Long assigneeId,
                           @Param("projectId") Long projectId,
                           @Param("completed") Boolean completed);
}
