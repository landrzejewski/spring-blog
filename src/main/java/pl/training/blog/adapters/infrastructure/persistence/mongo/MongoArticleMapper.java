package pl.training.blog.adapters.infrastructure.persistence.mongo;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import pl.training.blog.application.ArticleView;
import pl.training.blog.common.PageDefinition;
import pl.training.blog.common.ResultPage;
import pl.training.blog.domain.Article;

@Mapper(componentModel = "spring")
public interface MongoArticleMapper {

    Article toDomain(ArticleDocument articleDocument);

    ArticleDocument toDocument(Article article);

    default Pageable toEntity(PageDefinition pageDefinition) {
        return PageRequest.of(pageDefinition.pageNumber(), pageDefinition.pageSize());
    }

    @Mapping(source = "content", target = "elements")
    @Mapping(source = "number", target = "pageNumber")
    ResultPage<ArticleView> toDomain(Page<ArticleDocument> articleDocumentPage);

}
