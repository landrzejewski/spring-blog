package pl.training.blog.adapters.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

import java.util.UUID;

@Table(name = "Tags", indexes = @Index(name = "article_tag", columnList = "name", unique = true))
@Entity(name = "Tags")
public class TagEntity {

    @Id
    private UUID id;
    private String name;

}
