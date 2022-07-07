package com.lifeblog.blog.service;


import com.lifeblog.blog.controller.payload.BlogPostDto;
import org.springframework.stereotype.Service;

public interface BlogPostService {
    BlogPostDto createBlogPost(BlogPostDto postDto);
}
