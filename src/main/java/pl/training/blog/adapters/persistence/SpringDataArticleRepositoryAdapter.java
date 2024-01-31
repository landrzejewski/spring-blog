package pl.training.blog.adapters.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.training.blog.application.ArticleView;
import pl.training.blog.application.ResultPage;
import pl.training.blog.domain.Article;
import pl.training.blog.domain.ArticleCategory;
import pl.training.blog.domain.Tag;
import pl.training.blog.ports.ArticleRepository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class SpringDataArticleRepositoryAdapter implements ArticleRepository {

    private final SpringDataArticleRepository articleRepository;

    @Override
    public Optional<Article> findById(UUID id) {
        return Optional.empty();
    }

    @Override
    public ResultPage<ArticleView> findByCategory(ArticleCategory category) {
        return null;
    }

    @Override
    public ResultPage<ArticleView> findByTags(Set<Tag> tags) {
        return null;
    }

    @Override
    public Article save(Article article) {
        return null;
    }

    @Override
    public void deleteById(UUID articleId) {

    }

}
