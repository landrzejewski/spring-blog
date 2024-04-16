package pl.training.blog;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.training.blog.application.ArticleTemplate;
import pl.training.blog.common.PageDefinition;
import pl.training.blog.ports.api.ArticleAuthorActionsApi;
import pl.training.blog.ports.api.ArticleSearchApi;

import static pl.training.blog.domain.ArticleCategory.IT;

@SpringBootApplication
@RequiredArgsConstructor
@Log
public class BlogApplication implements ApplicationRunner {

    private final ArticleAuthorActionsApi authorActions;
    private final ArticleSearchApi search;

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        var article = new ArticleTemplate("Test", "Jan Kowalski", "",  IT);
        var id = authorActions.create(article);
        log.info(search.findByUid(id).toString());
        search.findByCategory(IT, new PageDefinition(0, 10));
        search.findByCategory(IT, new PageDefinition(0, 10));
    }

}
