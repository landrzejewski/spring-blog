package pl.training.blog.adapters.infrastructure.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.training.blog.common.PageDefinition;
import pl.training.blog.common.ResultPage;
import pl.training.blog.ports.api.ArticleSearchApi;

import java.util.Set;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ArticleSearchRestController {

    private final ArticleSearchApi articleSearch;
    private final ArticleRestMapper articleRestMapper;

    @GetMapping("articles/{article-id}")
    public ResponseEntity<ArticleDto> findById(@PathVariable("article-id") UUID articleId) {
        var article = articleSearch.findByUid(articleId);
        var articleDto = articleRestMapper.toDto(article);
        return ResponseEntity.ok(articleDto);
    }

    @GetMapping("categories/{category-name}/articles")
    public ResponseEntity<ResultPage<ArticleViewDto>> findBy(
            @PathVariable("category-name") String categoryName,
            @RequestParam(required = false, name = "tag") Set<String> tagNames,
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "10") int pageSize) {
        var category = articleRestMapper.toDomainCategory(categoryName);
        var tags = articleRestMapper.toDomain(tagNames);
        var pageDefinition = new PageDefinition(pageNumber, pageSize);
        var resultPage = articleSearch.findByCategoryAndTags(category, tags, pageDefinition);
        var resultPageDto = articleRestMapper.toDto(resultPage);
        return ResponseEntity.ok(resultPageDto);
    }

}
