package pl.training.blog.ports.api;

import pl.training.blog.application.ArticleView;
import pl.training.blog.common.PageDefinition;
import pl.training.blog.common.ResultPage;
import pl.training.blog.domain.Article;
import pl.training.blog.domain.ArticleCategory;
import pl.training.blog.domain.Tag;

import java.util.Set;
import java.util.UUID;

public interface ArticleSearchApi {

    Article findByUid(UUID id);

    ResultPage<ArticleView> findByCategory(ArticleCategory category, PageDefinition pageDefinition);

    ResultPage<ArticleView> findByTags(Set<Tag> tags, PageDefinition pageDefinition);

    ResultPage<ArticleView> findByCategoryAndTags(ArticleCategory category, Set<Tag> tags, PageDefinition pageDefinition);

}
