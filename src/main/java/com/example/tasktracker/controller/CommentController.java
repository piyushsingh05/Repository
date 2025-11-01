package com.example.tasktracker.controller;

import com.example.tasktracker.entity.Comment;
import com.example.tasktracker.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public Comment addComment(@RequestParam Long taskId,
                              @RequestParam Long authorId,
                              @RequestParam String content) {
        return commentService.addComment(taskId, authorId, content);
    }
}
