package com.lifeblog.blog.service;


import com.lifeblog.blog.controller.payload.BlogPostDto;

import java.util.List;

public interface BlogPostService {
    BlogPostDto createBlogPost(BlogPostDto postDto);

    List<BlogPostDto> getAllBlogPosts();

    BlogPostDto getBlogPostById(long id);

    BlogPostDto updateBlogPost(BlogPostDto blogPostDto, long id);

    void deleteBlogPostById(long id);
}
