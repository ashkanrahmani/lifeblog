package com.lifeblog.blog.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") long blogPostId,
            @Valid @RequestBody CommentDto commentDto) {

        return new ResponseEntity<>(commentService.createComment(blogPostId, commentDto), HttpStatus.CREATED);
    }

    @GetMapping(value = "/posts/{postId}/comments", produces = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE }, consumes = {
                    MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public List<CommentDto> getCommentByBlogPostId(@PathVariable(value = "postId") long blogPostId) {
        return commentService.getCommentsByBlogPostId(blogPostId);
    }

    @GetMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(value = "postId") long blogPostId,
            @PathVariable(value = "commentId") long commentId) {
        return new ResponseEntity<>(commentService.getCommentById(blogPostId, commentId), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable(value = "postId") long blogPostId,
            @PathVariable(value = "commentId") long commentId,
            @Valid @RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(commentService.updateComment(blogPostId, commentId, commentDto), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteCommentById(@PathVariable(value = "postId") long blogPostId,
            @PathVariable(value = "commentId") long commentId) {
        commentService.deleteCommentById(blogPostId, commentId);
        return new ResponseEntity<>("Comment deleted.", HttpStatus.OK);
    }
}