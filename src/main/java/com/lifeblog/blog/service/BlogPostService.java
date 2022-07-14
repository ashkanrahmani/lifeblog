package com.lifeblog.blog.service;

import com.lifeblog.blog.controller.payload.BlogPostDto;
import com.lifeblog.blog.controller.payload.BlogPostResponse;

public interface BlogPostService {
    BlogPostDto createBlogPost(BlogPostDto postDto);

    BlogPostResponse getAllBlogPosts(int pageSize, int pageNo, String sortBy, String sortDir);

    BlogPostDto getBlogPostById(long id);

    BlogPostDto updateBlogPost(BlogPostDto blogPostDto, long id);

    void deleteBlogPostById(long id);
}
