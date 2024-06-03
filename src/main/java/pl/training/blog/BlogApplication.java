package pl.training.blog;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.training.blog.application.ArticleTemplate;
import pl.training.blog.ports.api.ArticleAuthorActionsApi;
import pl.training.blog.ports.api.ArticleSearchApi;

import static pl.training.blog.domain.ArticleCategory.IT;

@RequiredArgsConstructor
@Log
public class BlogApplication {

    public static void main(String[] args) {
            try (var container = new AnnotationConfigApplicationContext(BlogConfiguration.class)) {
                var authorActions = container.getBean(ArticleAuthorActionsApi.class);
                var search = container.getBean(ArticleSearchApi.class);

                var article = new ArticleTemplate("Test", "Jan Kowalski", "",  IT);
                var id = authorActions.create(article);
                log.info(search.findByUid(id).toString());
            }
     }

}
