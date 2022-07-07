package com.lifeblog.blog.service.impl;

import com.lifeblog.blog.controller.payload.BlogPostDto;
import com.lifeblog.blog.entity.BlogPost;
import com.lifeblog.blog.exception.ResourceNotFoundException;
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
    public List<BlogPostDto> getAllBlogPosts() {

        List<BlogPost> all = blogPostRepository.findAll();
        return all.stream().map(blogPost -> getDto(blogPost)).collect(Collectors.toList());
    }

    @Override
    public BlogPostDto getBlogPostById(long id) {
        return getDto(blogPostRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", String.valueOf(id))));
    }

    @Override
    public BlogPostDto updateBlogPost(BlogPostDto blogPostDto, long id) {
        BlogPost byId = blogPostRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", String.valueOf(id)));
        byId.setTitle(blogPostDto.getTitle());
        byId.setContent(blogPostDto.getContent());
        byId.setDescription(blogPostDto.getDescription());
        return getDto(blogPostRepository.save(byId));
    }

    @Override
    public void deleteBlogPostById(long id) {
        BlogPost byId = blogPostRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", String.valueOf(id)));
        blogPostRepository.delete(byId);
    }
}
