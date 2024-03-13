package pl.training.blog.ports.api;

import pl.training.blog.domain.Comment;

import java.util.UUID;

public interface ArticleReaderActionsApi {

    void like(UUID articleId);

    void dislike(UUID articleId);

    void comment(UUID articleId, Comment comment);

}
