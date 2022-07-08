package com.lifeblog.blog.controller.payload;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private long id;
    private String name;
    private String email;
    private String body;
}
