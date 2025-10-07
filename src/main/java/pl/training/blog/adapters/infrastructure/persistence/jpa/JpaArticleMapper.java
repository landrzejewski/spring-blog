package pl.training.blog.adapters.infrastructure.persistence.jpa;

import org.mapstruct.Mapper;
import pl.training.blog.domain.Article;
import pl.training.blog.domain.ArticleCategory;
import pl.training.blog.domain.Tag;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Mapper(componentModel = "spring")
public interface JpaArticleMapper {

    Article toDomain(ArticleEntity articleEntity);

    ArticleEntity toEntity(Article article);

    default String toEntity(ArticleCategory category) {
        return category.name();
    }

    default Set<String> toEntity(Set<Tag> tags) {
        return tags.stream().map(Tag::getName).collect(toSet());
    }

}
