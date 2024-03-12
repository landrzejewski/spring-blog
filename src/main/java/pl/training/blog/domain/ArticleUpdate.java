package pl.training.blog.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.training.blog.domain.Tag;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleUpdate {

    private String title;
    private String content;
    private Set<Tag> tags;

}
