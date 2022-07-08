package com.lifeblog.blog.repository;

import com.lifeblog.blog.entity.Comment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByBlogPostId(long id);
    
}
