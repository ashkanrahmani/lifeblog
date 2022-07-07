package com.lifeblog.blog.service;


import com.lifeblog.blog.controller.payload.BlogPostDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BlogPostService {
    BlogPostDto createBlogPost(BlogPostDto postDto);
    List<BlogPostDto> getAllPosts();

    BlogPostDto getPostById(long id);
}
