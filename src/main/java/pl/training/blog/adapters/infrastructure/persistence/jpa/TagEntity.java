package pl.training.blog.adapters.infrastructure.persistence.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "Tags")
@Getter
@Setter
@EqualsAndHashCode(of = "name")
public class TagEntity {

    @Id
    private String name;

}
