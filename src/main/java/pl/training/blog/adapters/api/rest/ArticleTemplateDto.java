package pl.training.blog.adapters.api.rest;

import lombok.Data;

@Data
public class ArticleTemplateDto {

    private String title;
    private String author;
    private String content;
    private String category;

}
