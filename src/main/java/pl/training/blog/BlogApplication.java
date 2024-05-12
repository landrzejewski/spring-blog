package pl.training.blog;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import pl.training.blog.application.ArticleTemplate;
import pl.training.blog.common.PageDefinition;
import pl.training.blog.ports.api.ArticleAuthorActionsApi;
import pl.training.blog.ports.api.ArticleSearchApi;

import static pl.training.blog.domain.ArticleCategory.IT;

@RequiredArgsConstructor
@Log
public class BlogApplication {

    public static void main(String[] args) {
        ArticleAuthorActionsApi authorActions = null;
        ArticleSearchApi search = null;

        var article = new ArticleTemplate("Test", "Jan Kowalski", "",  IT);
        var id = authorActions.create(article);
        log.info(search.findByUid(id).toString());
        search.findByCategory(IT, new PageDefinition(0, 10));
        search.findByCategory(IT, new PageDefinition(0, 10));
    }

}
