package com.lifeblog.blog.service;

import com.lifeblog.blog.controller.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long blogPostId, CommentDto commentDto);

    List<CommentDto> getCommentsByBlogPostId(long blogPostId);

    CommentDto getCommentById(long blogPostId, long commentId);

}
