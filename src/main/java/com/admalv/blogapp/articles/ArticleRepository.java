package com.admalv.blogapp.articles;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {
    ArticleEntity findBySlug(String slug);
}
