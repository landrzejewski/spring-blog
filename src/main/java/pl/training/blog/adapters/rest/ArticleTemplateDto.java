package pl.training.blog.adapters.rest;

import lombok.Data;

import java.util.List;

@Data
public class ArticleTemplateDto {

    private String title;
    private List<String> authors;
    private String content;
    private String category;

}
