package pl.training.blog.adapters.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.training.blog.common.web.LocationUri;
import pl.training.blog.ports.api.ArticleAuthorActionsApi;

import java.util.UUID;

@RequestMapping("articles")
@RestController
@RequiredArgsConstructor
public class ArticleAuthorsActionsRestController {

    private final ArticleRestMapper mapper;
    private final ArticleAuthorActionsApi authorActions;

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody ArticleTemplateDto articleTemplateDto) {
        var articleTemplate = mapper.toDomain(articleTemplateDto);
        var articleId = authorActions.create(articleTemplate);
        var locationUri = LocationUri.fromRequestWith(articleId.toString());
        return ResponseEntity.created(locationUri).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        authorActions.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("{id}")
    public ResponseEntity<Void> update(@PathVariable UUID id, @RequestBody ArticleUpdateDto articleUpdateDto) {
        var articleUpdate = mapper.toDomain(articleUpdateDto);
        authorActions.update(id, articleUpdate);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("published/{id}")
    public ResponseEntity<Void> publish(@PathVariable UUID id) {
        authorActions.publish(id);
        return ResponseEntity.noContent().build();
    }

}
