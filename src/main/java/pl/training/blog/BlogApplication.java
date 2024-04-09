package pl.training.blog;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.training.blog.application.ArticleTemplate;
import pl.training.blog.ports.api.ArticleAuthorActionsApi;
import pl.training.blog.ports.api.ArticleReaderActionsApi;
import pl.training.blog.ports.api.ArticleSearchApi;

import static pl.training.blog.domain.ArticleCategory.IT;

@SpringBootApplication
@RequiredArgsConstructor
@Log
public class BlogApplication implements ApplicationRunner {

    private final ArticleAuthorActionsApi authorActions;
    private final ArticleReaderActionsApi readerActions;
    private final ArticleSearchApi search;

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        var article = new ArticleTemplate("Test", "Jan Kowalski", "",  IT);
        var id = authorActions.create(article);
        readerActions.like(id);
        log.info(search.findByUid(id).toString());
    }

}
