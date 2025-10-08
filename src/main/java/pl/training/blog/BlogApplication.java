package pl.training.blog;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.training.blog.ports.api.ArticleAuthorActionsApi;
import pl.training.blog.ports.api.ArticleReaderActionsApi;
import pl.training.blog.ports.api.ArticleSearchApi;

@SpringBootApplication
@Log
@RequiredArgsConstructor
public class BlogApplication  {

    private final ArticleAuthorActionsApi authorActions;
    private final ArticleReaderActionsApi readerActions;
    private final ArticleSearchApi search;

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }

}
