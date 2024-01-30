package pl.training.blog.application;

import lombok.RequiredArgsConstructor;
import pl.training.blog.domain.Article;
import pl.training.blog.domain.ArticleCategory;
import pl.training.blog.domain.Tag;
import pl.training.blog.ports.ArticleRepository;

import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
public class ArticleAuthorActions {

    private final ArticleRepository articleRepository;

    public UUID create(ArticleTemplate articleTemplate) {
        var article = Article.builder()
                .title(articleTemplate.getTitle())
                .authors(articleTemplate.getAuthors())
                .content(articleTemplate.getContent())
                .category(articleTemplate.getCategory())
                .build();
        return articleRepository.save(article).getId();
    }

    public void delete(UUID articleUid) {
        articleRepository.deleteByUid(articleUid);
    }

    public void updateTitle(UUID articleUid, String newTitle) {
        articleRepository.apply(articleUid, article -> article.setTitle(newTitle));
    }

    public void updateContent(UUID articleUid, String newContent) {
        articleRepository.apply(articleUid, article -> article.setContent(newContent));
    }

    public void updateCategory(UUID articleUid, ArticleCategory newCategory) {
        articleRepository.apply(articleUid, article -> article.setCategory(newCategory));
    }

    public void setTags(UUID articleUid, Set<Tag> tags) {
        articleRepository.apply(articleUid, article -> article.setTags(tags));
    }

    public void publish(UUID articleUid) {
        articleRepository.apply(articleUid, Article::publish);
    }

}
