package pl.training.blog.adapters.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.training.blog.common.web.LocationUri;
import pl.training.blog.ports.api.ArticleAuthorActionsApi;

import java.util.UUID;

@RequestMapping("articles")
@RestController
@RequiredArgsConstructor
public class ArticleAuthorActionsRestController {

    private final ArticleAuthorActionsApi articleAuthorActions;
    private final ArticleRestMapper articleRestMapper;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody ArticleTemplateDto articleTemplateDto) {
        var articleTemplate = articleRestMapper.toDomain(articleTemplateDto);
        var articleId = articleAuthorActions.create(articleTemplate);
        var locationUri = LocationUri.fromRequestWith(articleId);
        return ResponseEntity.created(locationUri).build();
    }

    @DeleteMapping("{article-id}")
    public ResponseEntity<Void> delete(@PathVariable("article-id") UUID articleId) {
        articleAuthorActions.delete(articleId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("{article-id}")
    public ResponseEntity<Void> update(@PathVariable("article-id") UUID articleId, @RequestBody ArticleUpdateDto articleUpdateDto) {
        var articleUpdate = articleRestMapper.toDomain(articleUpdateDto);
        articleAuthorActions.update(articleId, articleUpdate);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("published/{article-id}")
    public ResponseEntity<Void> publish(@PathVariable("article-id") UUID articleId) {
        articleAuthorActions.publish(articleId);
        return ResponseEntity.noContent().build();
    }

}
