package pl.training.blog.application;

import lombok.Value;
import pl.training.blog.domain.ArticleCategory;

import java.util.List;

@Value
public class ArticleTemplate {

    String title;
    List<String> authors;
    String content;
    ArticleCategory category;

}
