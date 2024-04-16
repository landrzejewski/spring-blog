package pl.training.blog.adapters.infrastructure.persistence.mongo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.training.blog.adapters.infrastructure.persistence.jpa.CommentEntity;
import pl.training.blog.adapters.infrastructure.persistence.jpa.TagEntity;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Document(collection = "articles")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class ArticleDocument {

    @Id
    private UUID id;
    private String title;
    private String author;
    private String content;
    private String category;
    private String status;
    private Set<TagEntity> tags;
    private Instant created;
    private Instant published;
    private int likes;
    private int dislikes;
    private List<CommentEntity> comments;

}
