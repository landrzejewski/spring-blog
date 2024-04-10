package pl.training.blog.common.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.training.blog.ports.infrastructure.ArticleRepository;

import java.util.function.Predicate;

@Component("uniqueTitle")
@RequiredArgsConstructor
public class UniqueTitlePredicate implements Predicate<String> {

    private final ArticleRepository articleRepository;

    @Override
    public boolean test(String title) {
        return articleRepository.existsByTitle(title);
    }

}
