package pl.training.blog.application;

import lombok.RequiredArgsConstructor;
import pl.training.blog.domain.Article;
import pl.training.blog.domain.ArticleCategory;
import pl.training.blog.domain.Tag;
import pl.training.blog.ports.ArticleRepository;

import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
public class ArticleSearch {

    private final ArticleRepository articleRepository;

    public Article findByUid(UUID id) {
        return articleRepository.findById(id)
                .orElseThrow(ArticleNotFoundException::new);
    }

    public ResultPage<ArticleView> findByCategory(ArticleCategory category) {
        return articleRepository.findByCategory(category);
    }

    public ResultPage<ArticleView> findByTags(Set<Tag> tags) {
        return articleRepository.findByTags(tags);
    }

}
