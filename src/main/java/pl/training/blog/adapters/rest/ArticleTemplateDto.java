package pl.training.blog.adapters.rest;

import lombok.Data;
import lombok.Value;
import pl.training.blog.common.validation.Condition;
import pl.training.blog.domain.ArticleCategory;

@Data
public class ArticleTemplateDto {

    @Condition("uniqueTitle")
    private String title;
    private String author;
    private String content;
    private String category;

}
