package pl.training.blog.adapters.rest;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import pl.training.blog.application.ArticleTemplate;
import pl.training.blog.domain.ArticleUpdate;
import pl.training.blog.domain.Tag;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface ArticleAuthorActionsMapper {

    ArticleTemplate toDomain(ArticleTemplateDto articleTemplateDto);

    ArticleUpdate toDomain(ArticleUpdateDto articleUpdateDto);

    default Tag toDomain(String tagName) {
        return new Tag(tagName);
    }
}
