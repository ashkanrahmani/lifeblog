package com.lifeblog.blog.service;

import com.lifeblog.blog.controller.payload.CommentDto;

public interface CommentService {
    CommentDto createComment(long blogPostId, CommentDto commentDto);
    
}
