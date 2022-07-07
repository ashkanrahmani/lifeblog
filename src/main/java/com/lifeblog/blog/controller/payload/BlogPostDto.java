package com.lifeblog.blog.controller.payload;

import lombok.Data;

@Data
public class BlogPostDto {
    private Long id;
    private String title;
    private String description;
    private String content;
}
