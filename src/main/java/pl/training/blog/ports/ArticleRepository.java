package pl.training.blog.ports;

import pl.training.blog.application.ArticleNotFoundException;
import pl.training.blog.application.ArticleView;
import pl.training.blog.application.ResultPage;
import pl.training.blog.domain.Article;
import pl.training.blog.domain.ArticleCategory;
import pl.training.blog.domain.Tag;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;

public interface ArticleRepository {

    Optional<Article> findById(UUID id);

    ResultPage<ArticleView> findByCategory(ArticleCategory category);

    ResultPage<ArticleView> findByTags(Set<Tag> tags);

    Article save(Article article);

    void deleteByUid(UUID articleId);

    default void apply(UUID articleId, Consumer<Article> operation) {
        var article = findById(articleId)
                .orElseThrow(ArticleNotFoundException::new);
        operation.accept(article);
        save(article);
    }

}
