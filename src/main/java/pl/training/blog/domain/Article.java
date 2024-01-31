package pl.training.blog.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static java.util.Collections.emptySet;
import static lombok.AccessLevel.PRIVATE;
import static pl.training.blog.domain.ArticleStatus.DRAFT;
import static pl.training.blog.domain.ArticleStatus.PUBLISHED;

@Builder
@AllArgsConstructor(access = PRIVATE)
public class Article {

    @Builder.Default
    @Getter
    private UUID id = UUID.randomUUID();
    @Setter
    private String title;
    private List<String> authors;
    @Setter
    private String content;
    @Setter
    private ArticleCategory category;
    @Builder.Default
    private ArticleStatus status = DRAFT;
    @Builder.Default
    @Setter
    private Set<Tag> tags = emptySet();
    @Builder.Default
    private Instant created = Instant.now();
    private Instant published;
    private int likes;
    private int dislikes;
    @Builder.Default
    private List<Comment> comments = new ArrayList<>();

    public void like() {
        likes++;
    }

    public void dislike() {
        dislikes++;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public void publish() {
        status = PUBLISHED;
        published = Instant.now();
    }

}
