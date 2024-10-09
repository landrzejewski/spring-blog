package pl.training.blog.adapters.infrastructure.rest;

import lombok.Data;

@Data
public class ArticleTemplateDto {

    @Conditions({"uniqueTitle", "minLength"})
    private String title;
    private String author;
    private String content;
    private String category;

}
