package pl.training.blog;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.*;
import pl.training.blog.adapters.infrastructure.events.SpringArticleEventsListener;
import pl.training.blog.adapters.infrastructure.events.SpringEventsEmitter;
import pl.training.blog.adapters.infrastructure.persistence.HashMapArticleRepository;
import pl.training.blog.application.ArticleAuthorActions;
import pl.training.blog.application.ArticleReaderActions;
import pl.training.blog.application.ArticleSearch;
import pl.training.blog.common.cache.CacheAspect;
import pl.training.blog.domain.Article;
import pl.training.blog.ports.api.ArticleAuthorActionsApi;
import pl.training.blog.ports.api.ArticleReaderActionsApi;
import pl.training.blog.ports.api.ArticleSearchApi;
import pl.training.blog.ports.infrastructure.ArticleRepository;
import pl.training.blog.ports.infrastructure.EventsEmitter;

@EnableAspectJAutoProxy
@ComponentScan
@Configuration
public class BlogConfiguration {

    @Primary
    @Bean
    public ArticleRepository articleRepository() {
        return new HashMapArticleRepository();
    }

    @Bean
    public ArticleAuthorActionsApi articleAuthorActions(@Qualifier("articleRepository") ArticleRepository articleRepository) {
        return new ArticleAuthorActions(articleRepository);
    }

    @Bean
    public ArticleReaderActionsApi articleReaderActions(ArticleRepository articleRepository, EventsEmitter eventsEmitter) {
        return new ArticleReaderActions(articleRepository, eventsEmitter);
    }

    @Bean
    public ArticleSearchApi articleSearch(ArticleRepository articleRepository) {
        return new ArticleSearch(articleRepository);
    }

    @Bean
    public CacheAspect cacheAspect() {
        return new CacheAspect(256);
    }

    @Bean
    public EventsEmitter eventsEmitter(ApplicationEventPublisher publisher) {
        return new SpringEventsEmitter(publisher);
    }

    @Bean
    public SpringArticleEventsListener eventsListener() {
        return new SpringArticleEventsListener();
    }

}
