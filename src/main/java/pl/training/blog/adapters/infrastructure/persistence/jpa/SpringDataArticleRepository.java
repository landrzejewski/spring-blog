package pl.training.blog.adapters.infrastructure.persistence.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.training.blog.application.ArticleView;

import java.util.Set;
import java.util.UUID;

public interface SpringDataArticleRepository extends JpaRepository<ArticleEntity, UUID> {

    boolean existsByTitle(String title);

    @Query("select new pl.training.blog.application.ArticleView(a.id, a.title, a.author) from Articles a where a.category = :category")
    Page<ArticleView> findByCategory(String category, Pageable pageable);

    @Query("select new pl.training.blog.application.ArticleView(a.id, a.title, a.author) from Articles a join a.tags t where t.name in :tags group by a having count (a) = :count")
    Page<ArticleView> findByTags(Set<String> tags, int count, Pageable pageable);

    @Query("select new pl.training.blog.application.ArticleView(a.id, a.title, a.author) from Articles a join a.tags t where a.category = :category and t.name in :tags group by a having count (a) = :count")
    Page<ArticleView> findByCategoryAndTags(String category, Set<String> tags, int count, Pageable pageable);

}
