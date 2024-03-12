package pl.training.blog;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.training.blog.application.ArticleAuthorActions;
import pl.training.blog.application.ArticleReaderActions;
import pl.training.blog.application.ArticleSearch;
import pl.training.blog.application.ArticleTemplate;
import pl.training.blog.domain.ArticleCategory;
import pl.training.blog.domain.ArticleUpdate;
import pl.training.blog.domain.Comment;
import pl.training.blog.domain.Tag;

import java.time.Instant;
import java.util.Set;

@Log
@SpringBootApplication
@RequiredArgsConstructor
public class BlogApplication implements ApplicationRunner {

    private final ArticleAuthorActions authorActions;
    private final ArticleReaderActions readerActions;
    private final ArticleSearch articleSearch;

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        var articleTemplate = new ArticleTemplate("Spring in action", "Jan Kowalski", "Spring framework is...", ArticleCategory.IT);
        var id = authorActions.create(articleTemplate);
        var articleUpdate = new ArticleUpdate("Spring in action", "...", Set.of(new Tag("it"), new Tag("Java")));
        authorActions.update(id, articleUpdate);
        authorActions.publish(id);
        readerActions.like(id);
        readerActions.comment(id, new Comment("Great article!", Instant.now(), "Nowak"));
        var article = articleSearch.findByUid(id);
        log.info(article.toString());
    }

}
