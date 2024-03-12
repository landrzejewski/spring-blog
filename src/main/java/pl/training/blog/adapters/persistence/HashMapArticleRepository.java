package pl.training.blog.adapters.persistence;

import org.springframework.stereotype.Repository;
import pl.training.blog.application.ArticleView;
import pl.training.blog.common.PageDefinition;
import pl.training.blog.common.ResultPage;
import pl.training.blog.domain.Article;
import pl.training.blog.domain.ArticleCategory;
import pl.training.blog.domain.Tag;
import pl.training.blog.ports.ArticleRepository;

import java.util.*;

@Repository
public class HashMapArticleRepository implements ArticleRepository {

    private final Map<UUID, Article> articleMap;

    public HashMapArticleRepository() {
        this.articleMap = new HashMap<>();
    }

    @Override
    public Optional<Article> findById(UUID id) {
        return Optional.ofNullable(articleMap.get(id));
    }

    @Override
    public ResultPage<ArticleView> findByCategory(ArticleCategory category, PageDefinition pageDefinition) {
        // Implement logic to find articles by category
        return null;
    }

    @Override
    public ResultPage<ArticleView> findByTags(Set<Tag> tags, PageDefinition pageDefinition) {
        // Implement logic to find articles by tags
        return null;
    }

    @Override
    public Article save(Article article) {
        articleMap.put(article.getId(), article);
        return article;
    }

    @Override
    public void deleteById(UUID articleId) {
        articleMap.remove(articleId);
    }

    @Override
    public ResultPage<ArticleView> findByCategoryAndTags(ArticleCategory category, Set<Tag> tags, PageDefinition pageDefinition) {
        // Implement logic to find articles by category and tags
        return null;
    }

}
