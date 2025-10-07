package pl.training.blog.adapters.infrastructure.persistence.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
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

@Component
@RequiredArgsConstructor
public class JpaArticleRepositoryAdapter implements ArticleRepository {

    // private final JpaArticleRepository articleRepository;
    private final SpringDataArticleRepository articleRepository;
    private final JpaArticleMapper articleMapper;

    @Override
    public Optional<Article> findById(UUID id) {
        return articleRepository.findById(id)
                .map(articleMapper::toDomain);
    }

    @Override
    public ResultPage<ArticleView> findByCategory(ArticleCategory category, PageDefinition pageDefinition) {
        return null;
    }

    @Override
    public ResultPage<ArticleView> findByTags(Set<Tag> tags, PageDefinition pageDefinition) {
        return null;
    }

    @Override
    public Article save(Article article) {
        var articleEntity = articleMapper.toEntity(article);
        var savedArticleEntity = articleRepository.save(articleEntity);
        return articleMapper.toDomain(savedArticleEntity);
    }

    @Override
    public void deleteById(UUID articleId) {

    }

    @Override
    public ResultPage<ArticleView> findByCategoryAndTags(ArticleCategory category, Set<Tag> tags, PageDefinition pageDefinition) {
        return null;
    }

    @Override
    public void update(Article article) {
        var articleEntity = articleMapper.toEntity(article);
        // articleRepository.update(articleEntity);
        articleRepository.save(articleEntity);
    }

    @Override
    public boolean existsByTitle(String title) {
        return false;
    }

}
