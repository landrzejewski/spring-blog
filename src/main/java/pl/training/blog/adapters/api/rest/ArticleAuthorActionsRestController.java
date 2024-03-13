package pl.training.blog.adapters.api.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.training.blog.ports.api.ArticleAuthorActionsApi;
import pl.training.blog.common.web.LocationUri;

import java.util.UUID;

@RequestMapping("articles")
@RestController
@RequiredArgsConstructor
public class ArticleAuthorActionsRestController {

    private final ArticleAuthorActionsApi articleAuthorActionsApi;
    private final ArticleRestMapper articleMapper;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody ArticleTemplateDto articleTemplateDto) {
        var articleTemplate = articleMapper.toDomain(articleTemplateDto);
        var articleId = articleAuthorActionsApi.create(articleTemplate);
        var locationUri = LocationUri.fromRequest(articleId);
        return ResponseEntity.created(locationUri).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        articleAuthorActionsApi.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("{id}")
    public ResponseEntity<Void> patch(@PathVariable UUID id, @RequestBody ArticleUpdateDto articleUpdateDto) {
        var articleUpdate = articleMapper.toDomain(articleUpdateDto);
        articleAuthorActionsApi.update(id, articleUpdate);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("published/{id}")
    public ResponseEntity<Void> publish(@PathVariable UUID id) {
        articleAuthorActionsApi.publish(id);
        return ResponseEntity.noContent().build();
    }

}
