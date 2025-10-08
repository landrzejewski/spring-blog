package pl.training.blog.adapters.infrastructure.persistence.rest;

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

    private final ArticleRestMapper mapper;
    private final ArticleSearchApi articleSearch;

    @GetMapping("articles/{id}")
    public ResponseEntity<ArticleDto> findById(@PathVariable UUID id) {
        var article = articleSearch.findByUid(id);
        var articleDto = mapper.toDto(article);
        return ResponseEntity.ok().body(articleDto);
    }

    @GetMapping("categories/{category-name}/articles")
    public ResponseEntity<ResultPage<ArticleViewDto>> findBy(
            @PathVariable("category-name") String categoryName,
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "10") int pageSize,
            @RequestParam(required = false, name = "tag") Set<String> tagNames) {
        var category = mapper.toDomainCategory(categoryName);
        var tags = mapper.toDomain(tagNames);
        var pageDefinition = new PageDefinition(pageNumber, pageSize);
        var resultPage = articleSearch.findByCategoryAndTags(category, tags, pageDefinition);
        var resultPageDto = mapper.toDto(resultPage);
        return ResponseEntity.ok(resultPageDto);
    }

}
