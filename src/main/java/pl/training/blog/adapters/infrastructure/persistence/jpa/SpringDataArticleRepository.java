package pl.training.blog.adapters.infrastructure.persistence.jpa;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface SpringDataArticleRepository extends CrudRepository<ArticleEntity, UUID> {
}
