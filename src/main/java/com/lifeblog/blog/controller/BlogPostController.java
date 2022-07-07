package com.lifeblog.blog.controller;

import com.lifeblog.blog.controller.payload.BlogPostDto;
import com.lifeblog.blog.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class BlogPostController {
    private final BlogPostService blogPostService;

    @Autowired
    public BlogPostController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }


    @PostMapping
    public ResponseEntity<BlogPostDto> createBlogPost(@RequestBody BlogPostDto postDto) {
        return new ResponseEntity<>(blogPostService.createBlogPost(postDto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<BlogPostDto> getAllBlogPost() {
        return blogPostService.getAllPosts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogPostDto> getBlogPostById(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(blogPostService.getPostById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BlogPostDto> updateBlogPostById(@RequestBody BlogPostDto blogPostDto,
                                                          @PathVariable(name = "id") long id){
        BlogPostDto updatePost = blogPostService.updatePost(blogPostDto, id);
        return new ResponseEntity<>(updatePost, HttpStatus.OK);
    }
}
