package com.lifeblog.blog.controller;

import com.lifeblog.blog.controller.payload.BlogPostDto;
import com.lifeblog.blog.controller.payload.BlogPostResponse;
import com.lifeblog.blog.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Struct;
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
    public BlogPostResponse getAllBlogPost(@RequestParam(name = "pageNo", defaultValue = "${app.default.page.number}", required = false) int pageNo,
                                           @RequestParam(name = "pageSize", defaultValue = "${app.default.page.size}", required = false) int pageSize,
                                           @RequestParam(name = "sortBy", defaultValue = "${app.default.sort.by}", required = false) String sortBy,
                                           @RequestParam(name = "sortDir", defaultValue = "${app.default.sort.dir}", required = false) String sortDir) {
        return blogPostService.getAllBlogPosts(pageSize,pageNo, sortBy, sortDir);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogPostDto> getBlogPostById(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(blogPostService.getBlogPostById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BlogPostDto> updateBlogPostById(@RequestBody BlogPostDto blogPostDto, @PathVariable(name = "id") long id) {
        BlogPostDto updatePost = blogPostService.updateBlogPost(blogPostDto, id);
        return new ResponseEntity<>(updatePost, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBlogPost(@PathVariable(name = "id") long id) {
        blogPostService.deleteBlogPostById(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
