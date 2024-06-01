package pl.training.blog.application;

import lombok.RequiredArgsConstructor;
import pl.training.blog.domain.Article;
import pl.training.blog.domain.ArticleUpdate;
import pl.training.blog.ports.api.ArticleAuthorActionsApi;
import pl.training.blog.ports.infrastructure.ArticleRepository;

import java.util.UUID;

@RequiredArgsConstructor
public class ArticleAuthorActions implements ArticleAuthorActionsApi {

    private final ArticleRepository articleRepository;

    @Override
    public UUID create(ArticleTemplate articleTemplate) {
        var article = Article.builder()
                .title(articleTemplate.getTitle())
                .author(articleTemplate.getAuthor())
                .content(articleTemplate.getContent())
                .category(articleTemplate.getCategory())
                .build();
        return articleRepository.save(article).getId();
    }

    @Override
    public void delete(UUID articleId) {
        articleRepository.deleteById(articleId);
    }

    @Override
    public void update(UUID articleId, ArticleUpdate articleUpdate) {
        articleRepository.apply(articleId, article -> article.patch(articleUpdate));
    }

    @Override
    public void publish(UUID articleId) {
        articleRepository.apply(articleId, Article::publish);
    }

}
