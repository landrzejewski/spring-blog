package pl.training.blog.adapters.api.rest;

import lombok.Data;

import java.util.UUID;

@Data
public class ArticleViewDto {

    private UUID id;
    private String title;
    private String author;

}
