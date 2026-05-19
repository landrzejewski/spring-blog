package pl.training.blog.adapters.rest;

import org.mapstruct.Mapper;
import pl.training.blog.application.ArticleTemplate;
import pl.training.blog.application.ArticleView;
import pl.training.blog.common.ResultPage;
import pl.training.blog.domain.Article;
import pl.training.blog.domain.ArticleCategory;
import pl.training.blog.domain.ArticleUpdate;
import pl.training.blog.domain.Tag;

import java.util.Set;

import static java.util.Collections.emptySet;
import static java.util.stream.Collectors.toSet;

@Mapper(componentModel = "spring")
public interface ArticleRestMapper {

    ArticleTemplate toDomain(ArticleTemplateDto articleTemplateDto);

    ArticleUpdate toDomain(ArticleUpdateDto articleUpdateDto);

    default Set<Tag> toDomain(Set<String> tags) {
        if (tags == null) {
            return emptySet();
        }
        return tags.stream().map(Tag::new).collect(toSet());
    }

    ArticleDto toDto(Article article);

    ArticleCategory toDomainCategory(String categoryName);

    ResultPage<ArticleViewDto> toDto(ResultPage<ArticleView> articleViewResultPage);

}
