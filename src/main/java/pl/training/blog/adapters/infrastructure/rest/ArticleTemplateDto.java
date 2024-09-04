package pl.training.blog.adapters.infrastructure.rest;

import lombok.Data;

@Data
public class ArticleTemplateDto {

    @Condition({"uniqueTitle", "minLength"})
    private String title;
    private String author;
    private String content;
    private String category;

}
