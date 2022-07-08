package com.lifeblog.blog.service.impl;

import com.lifeblog.blog.controller.payload.CommentDto;
import com.lifeblog.blog.entity.BlogPost;
import com.lifeblog.blog.entity.Comment;
import com.lifeblog.blog.exception.ApplicationAPIException;
import com.lifeblog.blog.exception.ResourceNotFoundException;
import com.lifeblog.blog.repository.BlogPostRepository;
import com.lifeblog.blog.repository.CommentRepository;
import com.lifeblog.blog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        BlogPost blogPost = getBlogPost(blogPostId);

        comment.setBlogPost(blogPost);
        Comment savedComment = commentRepository.save(comment);
        return getDto(savedComment);
    }

    @Override
    public List<CommentDto> getCommentsByBlogPostId(long blogPostId) {
        List<Comment> comments = commentRepository.findByBlogPostId(blogPostId);
        return comments.stream().map(comment -> getDto(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(long blogPostId, long commentId) {
        BlogPost blogPost = getBlogPost(blogPostId);

        Comment comment = getComment(commentId);

        if (!comment.getBlogPost().getId().equals(blogPost.getId())) {
            throw new ApplicationAPIException(HttpStatus.BAD_REQUEST, "This comments is not belong to post");
        }

        return getDto(comment);
    }

    private Comment getComment(long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", String.valueOf(commentId)));
        return comment;
    }

    private BlogPost getBlogPost(long blogPostId) {
        BlogPost blogPost = blogPostRepository.findById(blogPostId).orElseThrow(() -> new ResourceNotFoundException("BlogPost", "id", String.valueOf(blogPostId)));
        return blogPost;
    }

    @Override
    public CommentDto updateComment(long blogPostId, long commentId, CommentDto commentDto) {
        BlogPost blogPost = getBlogPost(blogPostId);
        Comment comment = getComment(commentId);

        if (!comment.getBlogPost().getId().equals(blogPost.getId())) {
            throw new ApplicationAPIException(HttpStatus.BAD_REQUEST, "This comments is not belong to post");
        }

        comment.setBody(commentDto.getBody());
        comment.setEmail(commentDto.getEmail());
        comment.setName(commentDto.getName());

        return getDto(commentRepository.save(comment));
    }

    @Override
    public void deleteCommentById(long blogPostId, long commentId) {
        BlogPost blogPost = getBlogPost(blogPostId);
        Comment comment = getComment(commentId);

        if (!comment.getBlogPost().getId().equals(blogPost.getId())) {
            throw new ApplicationAPIException(HttpStatus.BAD_REQUEST, "This comments is not belong to post");
        }

        commentRepository.delete(comment);

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
