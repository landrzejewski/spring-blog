package pl.training.blog.common.validation;

import lombok.RequiredArgsConstructor;
import pl.training.blog.ports.infrastructure.ArticleRepository;

import java.util.function.Predicate;

@RequiredArgsConstructor
public class UniqueTitlePredicate implements Predicate<String> {

    private final ArticleRepository articleRepository;

    @Override
    public boolean test(String title) {
        return !articleRepository.existsByTitle(title);
    }

}
