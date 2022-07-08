package com.lifeblog.blog.service.impl;

import org.springframework.stereotype.Service;

import com.lifeblog.blog.controller.payload.CommentDto;
import com.lifeblog.blog.entity.BlogPost;
import com.lifeblog.blog.entity.Comment;
import com.lifeblog.blog.exception.ResourceNotFoundException;
import com.lifeblog.blog.repository.BlogPostRepository;
import com.lifeblog.blog.repository.CommentRepository;
import com.lifeblog.blog.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final BlogPostRepository blogPostRepository;

    public CommentServiceImpl(CommentRepository commentRepository, BlogPostRepository blogPostRepository) {
        this.commentRepository = commentRepository;
        this.blogPostRepository = blogPostRepository;
    }

    @Override
    public CommentDto createComment(long blogPostId, CommentDto commentDto) {
        Comment comment = getEntity(commentDto);
        BlogPost blogPost = blogPostRepository.findById(blogPostId)
                .orElseThrow(() -> new ResourceNotFoundException("BlogPost", "id", String.valueOf(blogPostId)));

        comment.setBlogPost(blogPost);
        Comment savedComment = commentRepository.save(comment);
        return getDto(savedComment);
    }

    private CommentDto getDto(Comment comment) {
        CommentDto dto = new CommentDto();
        dto.setId(comment.getId());
        dto.setName(comment.getName());
        dto.setBody(comment.getBody());
        dto.setEmail(comment.getEmail());
        return dto;
    }

    private Comment getEntity(CommentDto dto) {
        Comment entity = new Comment();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setBody(dto.getBody());
        entity.setEmail(dto.getEmail());
        return entity;
    }
}
