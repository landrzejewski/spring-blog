package pl.training.blog.application;

import lombok.RequiredArgsConstructor;
import pl.training.blog.domain.Article;
import pl.training.blog.domain.Comment;
import pl.training.blog.ports.api.ArticleReaderActionsApi;
import pl.training.blog.ports.infrastructure.ArticleRepository;
import pl.training.blog.ports.infrastructure.EventsEmitter;

import java.util.UUID;

@RequiredArgsConstructor
public class ArticleReaderActions implements ArticleReaderActionsApi {

    private final ArticleRepository articleRepository;
    private final EventsEmitter eventsEmitter;

    @Override
    public void like(UUID articleId) {
        articleRepository.apply(articleId, Article::like);
        eventsEmitter.emit(new ArticleEvent("liked", articleId));
    }

    @Override
    public void dislike(UUID articleId) {
        articleRepository.apply(articleId, Article::dislike);
    }

    @Override
    public void comment(UUID articleId, Comment comment) {
        articleRepository.apply(articleId, article -> article.addComment(comment));
    }

}
