package pl.training.blog.adapters.infrastructure.persistence.jpa;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static jakarta.persistence.CascadeType.*;

@Entity(name = "Articles")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class ArticleEntity {

    @Id
    private UUID id;
    @Column(nullable = false, length = 200)
    private String title;
    private String author;
    @Lob
    @Column(length = 4096)
    private String content;
    private String category;
    private String status;
    @ManyToMany(cascade = {PERSIST, MERGE}, fetch = FetchType.EAGER)
    private Set<TagEntity> tags;
    private Instant created;
    private Instant published;
    private int likes;
    private int dislikes;
    @JoinColumn(name = "article_id")
    @OneToMany(cascade = ALL, fetch = FetchType.EAGER)
    private List<CommentEntity> comments;

}
