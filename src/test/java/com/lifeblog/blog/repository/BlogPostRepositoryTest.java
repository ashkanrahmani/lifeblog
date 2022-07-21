package com.lifeblog.blog.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.lifeblog.blog.entity.BlogPost;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BlogPostRepositoryTest {

	@Autowired
	private BlogPostRepository underTestBlogPostRepository;

	@Test
	public void savePostSuccess() {
		// given
		BlogPost blogPost = new BlogPost();
		blogPost.setTitle("JUnit test title");
		blogPost.setDescription("JUnit test desc");
		blogPost.setContent("JUnit test content");
		
		// when
		BlogPost expected = underTestBlogPostRepository.save(blogPost);

		// then
		Assertions.assertThat(expected).usingRecursiveComparison().ignoringFields("id").isEqualTo(blogPost);
	}
}