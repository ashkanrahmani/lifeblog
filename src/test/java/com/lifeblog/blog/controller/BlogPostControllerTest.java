package com.lifeblog.blog.controller;

import com.lifeblog.blog.service.BlogPostService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(controllers = BlogPostController.class)
class BlogPostControllerTest {

    @MockBean
    private BlogPostService blogPostService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    @DisplayName("Success create post test endpoint")
    void createBlogPostSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/lifeblog/api/posts")).andExpect(MockMvcResultMatchers.status().is(200));
    }


}