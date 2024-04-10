package pl.training.blog.common.web;

import java.time.ZonedDateTime;

public record ExceptionDto(ZonedDateTime timestamp, String description) {

    public ExceptionDto(String description) {
        this(ZonedDateTime.now(), description);
    }

}
