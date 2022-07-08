package com.lifeblog.blog.controller.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {
    private Date date;
    private String message;
    private String details;
}
