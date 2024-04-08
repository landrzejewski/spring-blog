package pl.training.blog.ports.infrastructure;

import pl.training.blog.application.ArticleEvent;

public interface EventsEmitter {

    void emit(ArticleEvent articleEvent);

}
