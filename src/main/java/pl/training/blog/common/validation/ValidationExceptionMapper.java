package pl.training.blog.common.validation;

import org.mapstruct.Mapper;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static java.util.stream.Collectors.joining;

@Mapper(componentModel = "spring")
public interface ValidationExceptionMapper {

    String KEY_VALUE_SEPARATOR = " ";
    String DELIMITER = ", ";

    default String toValidationErrors(MethodArgumentNotValidException exception) {
        return exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField() + KEY_VALUE_SEPARATOR + fieldError.getDefaultMessage())
                .collect(joining(DELIMITER));
    }

}
