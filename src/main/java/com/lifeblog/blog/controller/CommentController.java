package com.lifeblog.blog.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lifeblog.blog.controller.payload.CommentDto;
import com.lifeblog.blog.service.CommentService;

@RestController
@RequestMapping("/api/")
public class CommentController {

    private final CommentService commentService; 

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") long blogPostId,
            @RequestBody CommentDto commentDto) {

        return new ResponseEntity<>(commentService.createComment(blogPostId, commentDto), HttpStatus.CREATED);
    }
}