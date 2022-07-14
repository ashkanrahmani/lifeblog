package com.lifeblog.blog.service.impl;

import com.lifeblog.blog.controller.payload.BlogPostDto;
import com.lifeblog.blog.controller.payload.BlogPostResponse;
import com.lifeblog.blog.entity.BlogPost;
import com.lifeblog.blog.exception.ApplicationAPIException;
import com.lifeblog.blog.exception.ResourceNotFoundExceptionMessage;
import com.lifeblog.blog.exception.ResourceNotFoundException;
import com.lifeblog.blog.repository.BlogPostRepository;
import com.lifeblog.blog.service.BlogPostService;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogPostServiceImpl implements BlogPostService {

    private final BlogPostRepository blogPostRepository;
    private final ModelMapper mapper;

    public BlogPostServiceImpl(BlogPostRepository blogPostRepository, ModelMapper mapper) {
        this.blogPostRepository = blogPostRepository;
        this.mapper = mapper;
    }

    @Override
    public BlogPostDto createBlogPost(BlogPostDto postDto) {
        BlogPost blogPost = getEntity(postDto);
        BlogPost save;
        try {
            save = blogPostRepository.save(blogPost);
        } catch (DataIntegrityViolationException e) {
            throw new ApplicationAPIException(e.getMessage(), HttpStatus.BAD_REQUEST, e.getMessage());
        }
        BlogPostDto blogPostDto = getDto(save);
        return blogPostDto;
    }

    private BlogPost getEntity(BlogPostDto dto) {
        return mapper.map(dto, BlogPost.class);
    }

    private BlogPostDto getDto(BlogPost entity) {
        return mapper.map(entity, BlogPostDto.class);
    }

    @Override
    public BlogPostResponse getAllBlogPosts(int pageSize, int pageNo, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        PageRequest pageRequest = PageRequest.of(pageNo, pageSize, sort);
        Page<BlogPost> all = blogPostRepository.findAll(pageRequest);

        List<BlogPostDto> collect = all.getContent().stream().map(blogPost -> getDto(blogPost)).toList();
        return new BlogPostResponse(collect, all.getPageable().getPageNumber(), all.getPageable().getPageSize(),
                all.getTotalElements(), all.getTotalPages(), all.isLast());
    }

    @Override
    public BlogPostDto getBlogPostById(long id) {
        return getDto(blogPostRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundExceptionMessage.RESOURCE_NOT_FOUND.getErrorMessage(),
                        "Post", "id", String.valueOf(id))));
    }

    @Override
    public BlogPostDto updateBlogPost(BlogPostDto blogPostDto, long id) {
        BlogPost byId = blogPostRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundExceptionMessage.RESOURCE_NOT_FOUND.getErrorMessage(),
                        "Post", "id", String.valueOf(id)));
        byId.setTitle(blogPostDto.getTitle());
        byId.setContent(blogPostDto.getContent());
        byId.setDescription(blogPostDto.getDescription());
        return getDto(blogPostRepository.save(byId));
    }

    @Override
    public void deleteBlogPostById(long id) {
        BlogPost byId = blogPostRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundExceptionMessage.RESOURCE_NOT_FOUND.getErrorMessage(),
                        "Post", "id", String.valueOf(id)));
        blogPostRepository.delete(byId);
    }
}
