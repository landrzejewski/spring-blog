package pl.training.blog.ports.api;

import pl.training.blog.application.ArticleTemplate;
import pl.training.blog.domain.ArticleUpdate;

import java.util.UUID;

public interface ArticleAuthorActionsApi {

    UUID create(ArticleTemplate articleTemplate);

    void delete(UUID articleId);

    void update(UUID articleId, ArticleUpdate articleUpdate);

    void publish(UUID articleId);

}
