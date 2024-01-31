package pl.training.blog.adapters.rest;

import org.mapstruct.Mapper;
import pl.training.blog.application.ArticleTemplate;

@Mapper(componentModel = "spring")
public interface ArticleAuthorActionsMapper {

    ArticleTemplate toDomain(ArticleTemplateDto articleTemplateDto);

}
