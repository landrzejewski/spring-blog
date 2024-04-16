package pl.training.blog;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import pl.training.blog.application.ArticleAuthorActions;
import pl.training.blog.application.ArticleReaderActions;
import pl.training.blog.application.ArticleSearch;
import pl.training.blog.ports.api.ArticleAuthorActionsApi;
import pl.training.blog.ports.api.ArticleReaderActionsApi;
import pl.training.blog.ports.api.ArticleSearchApi;
import pl.training.blog.ports.infrastructure.ArticleRepository;
import pl.training.blog.ports.infrastructure.EventsEmitter;

@EnableAspectJAutoProxy
@ComponentScan
@Configuration
public class BlogConfiguration {

    @Bean
    public ArticleAuthorActionsApi articleAuthorActions(ArticleRepository articleRepository) {
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

}
