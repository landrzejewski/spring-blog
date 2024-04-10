package pl.training.blog.adapters.infrastructure.persistence.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.training.blog.domain.Article;

import java.util.UUID;

public interface MongoArticleRepository extends MongoRepository<Article, UUID> {

    boolean existsByTitle(String title);

}
