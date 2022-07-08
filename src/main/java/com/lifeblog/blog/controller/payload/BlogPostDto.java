package com.lifeblog.blog.controller.payload;

import lombok.*;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BlogPostDto {
    private Long id;
    private String title;
    private String description;
    private String content;
}
