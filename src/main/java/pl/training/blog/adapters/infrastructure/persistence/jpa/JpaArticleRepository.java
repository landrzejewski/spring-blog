package pl.training.blog.adapters.infrastructure.persistence.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaArticleRepository {

    @Setter
    @PersistenceContext
    private EntityManager entityManager;

    public Optional<ArticleEntity> findById(UUID id) {
        return Optional.ofNullable(entityManager.find(ArticleEntity.class, id));
    }

    public ArticleEntity save(ArticleEntity articleEntity) {
        entityManager.persist(articleEntity);
        return articleEntity;
    }

}
