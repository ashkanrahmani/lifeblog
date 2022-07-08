package com.lifeblog.blog.controller;

import com.lifeblog.blog.controller.payload.CommentDto;
import com.lifeblog.blog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") long blogPostId, @RequestBody CommentDto commentDto) {

        return new ResponseEntity<>(commentService.createComment(blogPostId, commentDto), HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getCommentByBlogPostId(@PathVariable(value = "postId") long blogPostId) {
        return commentService.getCommentsByBlogPostId(blogPostId);
    }

    @GetMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(value = "postId") long blogPostId, @PathVariable(value = "commentId") long commentId) {
        return new ResponseEntity<>(commentService.getCommentById(blogPostId, commentId), HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable(value = "postId") long blogPostId, @PathVariable(value = "commentId") long commentId, @RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(commentService.updateComment(blogPostId, commentId, commentDto), HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteCommentById(@PathVariable(value = "postId") long blogPostId, @PathVariable(value = "commentId") long commentId) {
        commentService.deleteCommentById(blogPostId, commentId);
        return new ResponseEntity<>("Comment deleted.", HttpStatus.OK);
    }
}