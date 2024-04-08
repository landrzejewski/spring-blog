package pl.training.blog;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.training.blog.application.ArticleTemplate;
import pl.training.blog.ports.api.ArticleAuthorActionsApi;
import pl.training.blog.ports.api.ArticleSearchApi;

import static pl.training.blog.domain.ArticleCategory.IT;

public class BlogApplication {

    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(BlogConfiguration.class)) {
            var authorActions = context.getBean(ArticleAuthorActionsApi.class);
            var article = new ArticleTemplate("Test", "Jan Kowalski", "",  IT);
            var id = authorActions.create(article);
            // var readerActions = context.getBean(ArticleReaderActionsApi.class);
            var search = context.getBean(ArticleSearchApi.class);
            System.out.println(search.findByUid(id));
        }
    }

}
