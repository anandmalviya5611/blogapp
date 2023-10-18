package com.admalv.blogapp.articles;

import com.admalv.blogapp.articles.DTOs.CreateArticleRequest;
import com.admalv.blogapp.users.UserRepository;
import com.admalv.blogapp.users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticlesService {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    public ArticlesService(ArticleRepository articleRepository, UserRepository userRepository) {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
    }

    public Iterable<ArticleEntity> getAllArticles(){
        return articleRepository.findAll();
    }

    public ArticleEntity findArticleBySlug(String slug){
        var article = articleRepository.findBySlug(slug);
        if(article == null){
            throw new ArticleNotFoundException(slug);
        }

        return article;
    }

    public ArticleEntity createArticle(CreateArticleRequest a, Long authorId){
        var author = userRepository.findById(authorId).orElseThrow(() -> new UsersService.UserNotFoundException(authorId));

        var newArticle = ArticleEntity.builder()
                .title(a.getTitle())
                .slug(a.getTitle().toLowerCase().replaceAll("\\s+", "-"))
                .body(a.getBody())
                .author(author)
                .build();

        return newArticle;
    }

    public ArticleEntity updateArticle(CreateArticleRequest a, Long articleId){
        var article = articleRepository.findById(articleId).orElseThrow(()-> new ArticleNotFoundException(articleId));

        if(a.getTitle() != null){
            article.setTitle(a.getTitle());
            article.setSlug(a.getTitle().toLowerCase().replaceAll("\\s+", "-"));
        }
        if(a.getBody() != null){
            article.setBody(a.getBody());
        }

        if(a.getSubtitle() != null){
            article.setSubtitle(a.getSubtitle());
        }

        return article;
    }


    static class ArticleNotFoundException extends IllegalArgumentException {
        public ArticleNotFoundException(String slug){
            super("Slug "+ slug + " not found");
        }
        public ArticleNotFoundException(Long articleId){
            super("article " + articleId + " does not exist");
        }

    }

}
