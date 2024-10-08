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

@Transactional
@Repository
@RequiredArgsConstructor
public class SpringDataRepositoryAdapter implements ArticleRepository {

    private final SpringDataArticleRepository articleRepository;
    private final SpringDataArticleMapper mapper;

    @Override
    public Optional<Article> findById(UUID id) {
        return articleRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Article save(Article article) {
        var articleEntity = mapper.toEntity(article);
        var savedArticleEntity = articleRepository.save(articleEntity);
        return mapper.toDomain(savedArticleEntity);
    }

    @Override
    public ResultPage<ArticleView> findByCategory(ArticleCategory category, PageDefinition pageDefinition) {
        var page = mapper.toEntity(pageDefinition);
        var categoryName = mapper.toEntity(category);
        var result = articleRepository.findByCategory(categoryName, page);
        return mapper.toDomain(result);
    }

    @Override
    public ResultPage<ArticleView> findByTags(Set<Tag> tags, PageDefinition pageDefinition) {
        var page = mapper.toEntity(pageDefinition);
        var tagNames = mapper.toEntity(tags);
        var result = articleRepository.findByTags(tagNames, tagNames.size(), page);
        return mapper.toDomain(result);
    }

    @Override
    public ResultPage<ArticleView> findByCategoryAndTags(ArticleCategory category, Set<Tag> tags, PageDefinition pageDefinition) {
        var page = mapper.toEntity(pageDefinition);
        var categoryName = mapper.toEntity(category);
        var tagNames = mapper.toEntity(tags);
        var result = articleRepository.findByCategoryAndTags(categoryName, tagNames, tagNames.size(), page);
        return mapper.toDomain(result);
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
