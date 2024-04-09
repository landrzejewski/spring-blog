package pl.training.blog.adapters.infrastructure.persistence.jpa;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import pl.training.blog.application.ArticleView;
import pl.training.blog.common.PageDefinition;
import pl.training.blog.common.ResultPage;
import pl.training.blog.domain.Article;
import pl.training.blog.domain.ArticleCategory;
import pl.training.blog.domain.Tag;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Mapper(componentModel = "spring")
public interface SpringDataArticleMapper {

    ArticleEntity toEntity(Article article);

    Article toDomain(ArticleEntity articleEntity);

    @Mapping(source = "content", target = "elements")
    @Mapping(source = "number", target = "pageNumber")
    ResultPage<ArticleView> toDomain(Page<ArticleView> articleEntitPage);

    default Pageable toEntity(PageDefinition pageDefinition) {
        return PageRequest.of(pageDefinition.pageNumber(), pageDefinition.pageSize());
    }

    default Set<String> toEntity(Set<Tag> tags) {
        return tags.stream().map(Tag::getName).collect(toSet());
    }

    default String toEntity(ArticleCategory category) {
        return category.name();
    }

}
