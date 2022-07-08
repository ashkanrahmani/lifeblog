package com.lifeblog.blog.service;


import com.lifeblog.blog.controller.payload.BlogPostDto;
import com.lifeblog.blog.controller.payload.BlogPostResponse;

import java.util.List;

public interface BlogPostService {
    BlogPostDto createBlogPost(BlogPostDto postDto);

    BlogPostResponse getAllBlogPosts(int pageSize, int pageNo);

    BlogPostDto getBlogPostById(long id);

    BlogPostDto updateBlogPost(BlogPostDto blogPostDto, long id);

    void deleteBlogPostById(long id);
}
