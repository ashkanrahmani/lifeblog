package com.lifeblog.blog.service.impl;

import com.lifeblog.blog.controller.payload.BlogPostDto;
import com.lifeblog.blog.controller.payload.CommentDto;
import com.lifeblog.blog.entity.BlogPost;
import com.lifeblog.blog.exception.ResourceNotFoundException;
import com.lifeblog.blog.repository.BlogPostRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.BDDAssumptions.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BlogPostServiceImplTest {

    // should be real class, because this is under test
    @InjectMocks
    BlogPostServiceImpl blogPostService;

    @Mock
    private BlogPostRepository blogPostRepository;

    @Mock
    private ModelMapper mapper;

    @BeforeAll
    static void beforeAll() {
        System.out.println("before all");
    }

    @BeforeEach
    void beforeEach() {
        blogPostService = new BlogPostServiceImpl(blogPostRepository, mapper);
    }

    @Test
    @DisplayName("Pass: save post to database")
    public void saveBlogPostSuccess() {

        when(blogPostRepository.findByTitle(anyString())).thenReturn(null);

        BlogPostDto blogPostDto = new BlogPostDto();
        blogPostDto.setId(1L);
        blogPostDto.setTitle("JUnit test title");
        blogPostDto.setDescription("JUnit test desc");
        blogPostDto.setContent("JUnit test content");

        Set<CommentDto> commentDtoList = new HashSet<>();
        CommentDto commentDto = new CommentDto();
        commentDto.setId(2L);
        commentDto.setBody("Comment test body");
        commentDto.setEmail("ashkan.rahmani@gmail.com");
        commentDto.setBody("this is body of comment for test");
        commentDtoList.add(commentDto);
        blogPostDto.setComments(commentDtoList);

//        // Inject the configuration mock
//        Configuration configurationMock = mock(Configuration.class);
//        when(configurationMock.setMatchingStrategy(MatchingStragegies.STRICT)
//                .thenReturn(configuraitonMock);
//        when(mapper.getConfiguration()).thenReturn(configurationMock);
        BlogPostDto blogPost = blogPostService.createBlogPost(blogPostDto);

        assertNotNull(blogPost);
        assertEquals(blogPost.getTitle(), blogPostDto.getTitle());
    }

    @Test

    @DisplayName("Pass: save post to database")
    public void saveBlogPostDuplicateTitleFailed() {
        BlogPostDto blogPostDto1 = new BlogPostDto();
        blogPostDto1.setTitle("JUnit test title");
        blogPostDto1.setDescription("JUnit test desc");
        blogPostDto1.setContent("JUnit test content");
        blogPostService.createBlogPost(blogPostDto1);
        BlogPostDto blogPostDto2 = new BlogPostDto();
        blogPostDto2.setTitle("JUnit test title");
        blogPostDto2.setDescription("JUnit test desc");
        blogPostDto2.setContent("JUnit test content");
        blogPostService.createBlogPost(blogPostDto2);
    }

    @Test
    @DisplayName("Pass: get list of all posts from database")
    public void getAllBlogPostsSuccess() {

    }

    @Test
    @DisplayName("Pass: post id is available")
    public void getBlogPostByIdSuccess() {
        BlogPost blogPost = new BlogPost();
        blogPost.setId(1L);
        blogPost.setTitle("JUnit test title");
        blogPost.setDescription("JUnit test desc");
        blogPost.setContent("JUnit test content");

        when(blogPostRepository.findById(anyLong())).thenReturn(Optional.of(blogPost));

        BlogPostDto realResult = blogPostService.getBlogPostById(12L);

//        assertNotNull(realResult);
        assertEquals("JUnit test title", realResult.getTitle());

    }

    @Test
    @DisplayName("fail: post id is NOT available")
    public void getBlogPostByIdFail() {
        when(blogPostRepository.findById(anyLong())).thenReturn(null);
        assertThrows(ResourceNotFoundException.class, () -> blogPostService.getBlogPostById(12L));
    }

}