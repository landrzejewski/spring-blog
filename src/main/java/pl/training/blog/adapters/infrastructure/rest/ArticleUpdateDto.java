package pl.training.blog.adapters.infrastructure.rest;

import lombok.Data;

import java.util.Set;

@Data
public class ArticleUpdateDto {

    private String title;
    private String content;
    private Set<String> tags;

}
