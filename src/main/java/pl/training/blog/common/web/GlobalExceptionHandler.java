package pl.training.blog.common.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import pl.training.blog.application.ArticleNotFoundException;
import pl.training.blog.common.validation.ValidationExceptionMapper;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice(annotations = RestController.class)
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final ValidationExceptionMapper exceptionMapper;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDto> onMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        var validationErrors = exceptionMapper.toValidationErrors(exception);
        return ResponseEntity.badRequest()
                .body(new ExceptionDto(validationErrors));
    }

    @ExceptionHandler(ArticleNotFoundException.class)
    public ResponseEntity<ExceptionDto> onArticleNotFoundException(ArticleNotFoundException exception) {
        return ResponseEntity.status(NOT_FOUND)
                .body(new ExceptionDto("Article not found"));
    }

}
