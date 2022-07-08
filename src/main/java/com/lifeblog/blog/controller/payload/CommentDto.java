package com.lifeblog.blog.controller.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private long id;

    @NotEmpty (message = "Name should not be empty")
    @Size(min = 2, message = "comment name should have at least 10 characters.")
    private String name;

    @Email(message = "Not in email format")
    private String email;

    @NotEmpty(message = "Body should not be empty")
    private String body;
}
