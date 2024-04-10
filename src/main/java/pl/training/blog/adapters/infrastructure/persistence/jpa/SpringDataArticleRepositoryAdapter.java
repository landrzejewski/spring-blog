package pl.training.blog.adapters.infrastructure.persistence.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
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

@Primary
@Transactional
@Repository
@RequiredArgsConstructor
public class SpringDataArticleRepositoryAdapter implements ArticleRepository {

    private final SpringDataArticleRepository articleRepository;
    private final SpringDataArticleMapper articleMapper;

    @Override
    public Optional<Article> findById(UUID id) {
        return articleRepository.findById(id)
                .map(articleMapper::toDomain);
    }

    @Override
    public ResultPage<ArticleView> findByCategory(ArticleCategory category, PageDefinition pageDefinition) {
        var page = articleMapper.toEntity(pageDefinition);
        var categoryName = articleMapper.toEntity(category);
        var result = articleRepository.findByCategory(categoryName, page);
        return articleMapper.toDomain(result);
    }

    @Override
    public ResultPage<ArticleView> findByTags(Set<Tag> tags, PageDefinition pageDefinition) {
        var page = articleMapper.toEntity(pageDefinition);
        var tagNames = articleMapper.toEntity(tags);
        var result = articleRepository.findByTags(tagNames, tagNames.size(), page);
        return articleMapper.toDomain(result);
    }

    @Override
    public Article save(Article article) {
        var articleEntity = articleMapper.toEntity(article);
        var savedArticleEntity = articleRepository.save(articleEntity);
        return articleMapper.toDomain(savedArticleEntity);
    }

    @Override
    public void deleteById(UUID articleId) {
        articleRepository.deleteById(articleId);
    }

    @Override
    public ResultPage<ArticleView> findByCategoryAndTags(ArticleCategory category, Set<Tag> tags, PageDefinition pageDefinition) {
        var page = articleMapper.toEntity(pageDefinition);
        var categoryName = articleMapper.toEntity(category);
        var tagNames = articleMapper.toEntity(tags);
        var result = articleRepository.findByCategoryAndTags(categoryName, tagNames, tagNames.size(), page);
        return articleMapper.toDomain(result);
    }

}
