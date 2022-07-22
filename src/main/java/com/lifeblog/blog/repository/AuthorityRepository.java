package com.lifeblog.blog.repository;

import com.lifeblog.blog.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

     Authority findByName(String name);
}
