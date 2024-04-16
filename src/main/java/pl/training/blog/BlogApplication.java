package pl.training.blog;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.training.blog.application.ArticleTemplate;
import pl.training.blog.common.PageDefinition;
import pl.training.blog.ports.api.ArticleAuthorActionsApi;
import pl.training.blog.ports.api.ArticleSearchApi;

import static pl.training.blog.domain.ArticleCategory.IT;

@RequiredArgsConstructor
@Log
public class BlogApplication {

    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(BlogConfiguration.class)) {
            var authorActions = context.getBean(ArticleAuthorActionsApi.class);
            var search = context.getBean(ArticleSearchApi.class);

            var article = new ArticleTemplate("Test", "Jan Kowalski", "",  IT);
            var id = authorActions.create(article);
            log.info(search.findByUid(id).toString());
            search.findByCategory(IT, new PageDefinition(0, 10));
            search.findByCategory(IT, new PageDefinition(0, 10));
        }

    }

}
