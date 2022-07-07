package com.lifeblog.blog.entity.payload;

import lombok.Data;

@Data
public class BlogPostDto {
    private Long id;
    private String title;
    private String description;
    private String content;
}
