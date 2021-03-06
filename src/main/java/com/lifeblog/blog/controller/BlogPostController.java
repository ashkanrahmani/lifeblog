package com.lifeblog.blog.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.lifeblog.blog.controller.payload.BlogPostDto;
import com.lifeblog.blog.controller.payload.BlogPostResponse;
import com.lifeblog.blog.service.BlogPostService;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin("*")
public class BlogPostController {

    private final BlogPostService blogPostService;

    public BlogPostController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<BlogPostDto> createBlogPost(@Valid @RequestBody BlogPostDto postDto) {
        return new ResponseEntity<>(blogPostService.createBlogPost(postDto), HttpStatus.CREATED);
    }

    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, consumes = {
            MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public BlogPostResponse getAllBlogPost(
            @RequestParam(name = "pageNo", defaultValue = "${app.api.default.page.number}", required = false) int pageNo,
            @RequestParam(name = "pageSize", defaultValue = "${app.api.default.page.size}", required = false) int pageSize,
            @RequestParam(name = "sortBy", defaultValue = "${app.api.default.sort.by}", required = false) String sortBy,
            @RequestParam(name = "sortDir", defaultValue = "${app.api.default.sort.dir}", required = false) String sortDir) {
        return blogPostService.getAllBlogPosts(pageSize, pageNo, sortBy, sortDir);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogPostDto> getBlogPostById(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(blogPostService.getBlogPostById(id));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<BlogPostDto> updateBlogPostById(@Valid @RequestBody BlogPostDto blogPostDto,
            @PathVariable(name = "id") long id) {
        BlogPostDto updatePost = blogPostService.updateBlogPost(blogPostDto, id);
        return new ResponseEntity<>(updatePost, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBlogPost(@PathVariable(name = "id") long id) {
        blogPostService.deleteBlogPostById(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    @GetMapping(value = "/version", headers = "X-API-VERSION=1")
    public ResponseEntity<String> getVersionV1() {
        return new ResponseEntity<>("Version 1", HttpStatus.OK);
    }

    @GetMapping(value = "/version", headers = "X-API-VERSION=2")
    public ResponseEntity<String> getVersionV2() {
        return new ResponseEntity<>("Version 2", HttpStatus.OK);
    }
}
