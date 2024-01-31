package pl.training.blog.adapters.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.training.blog.application.ArticleAuthorActions;

@RequestMapping("articles")
@RestController
@RequiredArgsConstructor
public class ArticleAuthorActionsRestController {

    private final ArticleAuthorActions articleAuthorActions;
    private final ArticleAuthorActionsMapper articleMapper;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody ArticleTemplateDto articleTemplateDto) {
        var articleTemplate = articleMapper.toDomain(articleTemplateDto);
        var articleId = articleAuthorActions.create(articleTemplate);
        var locationUri = LocationUri.fromRequest(articleId);
        return ResponseEntity.created(locationUri).build();
    }


}
