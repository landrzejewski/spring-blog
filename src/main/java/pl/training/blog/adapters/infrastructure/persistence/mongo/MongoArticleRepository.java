package pl.training.blog.adapters.infrastructure.persistence.mongo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import pl.training.blog.domain.ArticleCategory;

import java.util.UUID;

public interface MongoArticleRepository extends MongoRepository<ArticleDocument, UUID> {

    @Query(value = "{category: {$eq: ?0}}", fields = "{_id: 1, title: 1, author: 1}")
    Page<ArticleDocument> findByCategory(ArticleCategory category, Pageable pageable);

    boolean existsByTitle(String title);

}
