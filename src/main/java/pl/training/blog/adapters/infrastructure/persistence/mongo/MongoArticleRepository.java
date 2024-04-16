package pl.training.blog.adapters.infrastructure.persistence.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.training.blog.domain.Article;

import java.util.UUID;

public interface MongoArticleRepository extends MongoRepository<ArticleDocument, UUID> {

    boolean existsByTitle(String title);

}
