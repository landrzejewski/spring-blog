package pl.training.blog.adapters.infrastructure.persistence.mongo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.training.blog.application.ArticleView;
import pl.training.blog.common.PageDefinition;
import pl.training.blog.common.ResultPage;
import pl.training.blog.domain.Article;
import pl.training.blog.domain.ArticleCategory;
import pl.training.blog.domain.Tag;
import pl.training.blog.ports.infrastructure.ArticleRepository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Transactional
@Repository
@RequiredArgsConstructor
public class MongoArticleRepositoryAdapter implements ArticleRepository {

    private final MongoArticleRepository articleRepository;
    private final MongoDataArticleMapper articleMapper;

    @Override
    public Optional<Article> findById(UUID id) {
        return articleRepository.findById(id)
                .map(articleMapper::toDomain);
    }

    @Override
    public ResultPage<ArticleView> findByCategory(ArticleCategory category, PageDefinition pageDefinition) {
        return new ResultPage<>();
    }

    @Override
    public ResultPage<ArticleView> findByTags(Set<Tag> tags, PageDefinition pageDefinition) {
        return new ResultPage<>();
    }

    @Override
    public ResultPage<ArticleView> findByCategoryAndTags(ArticleCategory category, Set<Tag> tags, PageDefinition pageDefinition) {
        return new ResultPage<>();
    }

    @Override
    public Article save(Article article) {
        var articleDocument = articleMapper.toDocument(article);
        var savedArticleEntity = articleRepository.save(articleDocument);
        return articleMapper.toDomain(savedArticleEntity);
    }

    @Override
    public void deleteById(UUID articleId) {
        articleRepository.deleteById(articleId);
    }

    @Override
    public boolean existsByTitle(String title) {
        return articleRepository.existsByTitle(title);
    }

}
