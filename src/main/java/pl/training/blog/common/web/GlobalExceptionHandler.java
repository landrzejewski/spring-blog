package pl.training.blog.common.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import pl.training.blog.application.ArticleNotFoundException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler {

    @ExceptionHandler(ArticleNotFoundException.class)
    public ResponseEntity<ExceptionDto> onArticleNotFound() {
        return ResponseEntity.status(NOT_FOUND)
                .body(new ExceptionDto("Article not found"));
    }

}
