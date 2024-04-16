package pl.training.blog.adapters.infrastructure.persistence.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.training.blog.application.ArticleView;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static pl.training.blog.adapters.infrastructure.persistence.jpa.ArticleEntity.WITH_TAGS;


public interface SpringDataArticleRepository extends JpaRepository<ArticleEntity, UUID> {

    //@EntityGraph(attributePaths = {"tags", "comments"})
    @EntityGraph(WITH_TAGS)
    Optional<ArticleEntity> findById(UUID id);

    @Query("select new pl.training.blog.application.ArticleView(a.id, a.title, a.author) from Article a where a.category = :category")
    Page<ArticleView> findByCategory(String category, Pageable pageable);

    @Query("select new pl.training.blog.application.ArticleView(a.id, a.title, a.author) from Article a join a.tags t where t.name in :tags group by a having count (a) = :count")
    Page<ArticleView> findByTags(Set<String> tags, int count, Pageable pageable);

    @Query("select new pl.training.blog.application.ArticleView(a.id, a.title, a.author) from Article a join a.tags t where a.category = :category and t.name in :tags group by a having count (a) = :count")
    Page<ArticleView> findByCategoryAndTags(String category, Set<String> tags, int count, Pageable pageable);

    boolean existsByTitle(String title);

}
