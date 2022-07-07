package com.lifeblog.blog.repository;

import com.lifeblog.blog.entity.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {
}
