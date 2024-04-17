package pl.training.blog.adapters.infrastructure.rest;

import lombok.Data;
import pl.training.blog.common.validation.Condition;

@Data
public class ArticleTemplateDto {

    @Condition("uniqueTitle")
    private String title;
    private String author;
    private String content;
    private String category;

}
