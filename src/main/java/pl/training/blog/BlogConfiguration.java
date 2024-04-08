package pl.training.blog;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
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

@EnableAspectJAutoProxy
@ComponentScan
@Configuration
public class BlogConfiguration {

    @Bean
    public ArticleRepository articleRepository() {
        return new HashMapArticleRepository();
    }

    @Bean
    public ArticleAuthorActionsApi articleAuthorActions(ArticleRepository articleRepository) {
        return new ArticleAuthorActions(articleRepository);
    }

    @Bean
    public ArticleReaderActionsApi articleReaderActions(ArticleRepository articleRepository) {
        return new ArticleReaderActions(articleRepository);
    }

    @Bean
    public ArticleSearchApi articleSearch(ArticleRepository articleRepository) {
        return new ArticleSearch(articleRepository);
    }

    @Bean
    public CacheAspect cacheAspect() {
        return new CacheAspect(256);
    }

}
