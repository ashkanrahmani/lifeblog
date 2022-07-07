package com.lifeblog.blog.service.impl;

import com.lifeblog.blog.controller.payload.BlogPostDto;
import com.lifeblog.blog.entity.BlogPost;
import com.lifeblog.blog.repository.BlogPostRepository;
import com.lifeblog.blog.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogPostServiceImpl implements BlogPostService {

    private final BlogPostRepository blogPostRepository;

    @Autowired
    public BlogPostServiceImpl(BlogPostRepository blogPostRepository) {
        this.blogPostRepository = blogPostRepository;
    }

    @Override
    public BlogPostDto createBlogPost(BlogPostDto postDto) {

        BlogPost blogPost = getEntity(postDto);
        BlogPost save = blogPostRepository.save(blogPost);
        BlogPostDto blogPostDto = getDto(save);
        return blogPostDto;
    }

    private BlogPostDto getDto(BlogPost save) {

        BlogPostDto blogPostDto = new BlogPostDto();
        blogPostDto.setId(save.getId());
        blogPostDto.setTitle(save.getTitle());
        blogPostDto.setContent(save.getContent());
        blogPostDto.setDescription(save.getDescription());
        return blogPostDto;
    }

    private BlogPost getEntity(BlogPostDto postDto) {

        BlogPost blogPost = new BlogPost();
        blogPost.setTitle(postDto.getTitle());
        blogPost.setDescription(postDto.getContent());
        blogPost.setContent(postDto.getContent());
        return blogPost;
    }

    @Override
    public List<BlogPostDto> getAllPosts() {

        List<BlogPost> all = blogPostRepository.findAll();
        return all.stream().map(blogPost -> getDto(blogPost)).collect(Collectors.toList());
    }
}
