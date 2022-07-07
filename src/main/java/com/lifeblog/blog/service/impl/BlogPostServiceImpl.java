package com.lifeblog.blog.service.impl;

import com.lifeblog.blog.controller.payload.BlogPostDto;
import com.lifeblog.blog.entity.BlogPost;
import com.lifeblog.blog.repository.BlogPostRepository;
import com.lifeblog.blog.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogPostServiceImpl implements BlogPostService {

    private final BlogPostRepository blogPostRepository;

    @Autowired
    public BlogPostServiceImpl(BlogPostRepository blogPostRepository) {
        this.blogPostRepository = blogPostRepository;
    }

    @Override
    public BlogPostDto createBlogPost(BlogPostDto postDto) {

        BlogPost blogPost = new BlogPost();
        blogPost.setTitle(postDto.getTitle());
        blogPost.setDescription(postDto.getContent());
        blogPost.setContent(postDto.getContent());

        BlogPost save = blogPostRepository.save(blogPost);

        BlogPostDto blogPostDto = new BlogPostDto();
        blogPostDto.setId(save.getId());
        blogPostDto.setTitle(save.getTitle());
        blogPostDto.setContent(save.getContent());
        blogPostDto.setDescription(save.getDescription());

        return blogPostDto;
    }
}
