package pl.training.blog.adapters.infrastructure.events;

import lombok.extern.java.Log;
import pl.training.blog.application.ArticleEvent;
import pl.training.blog.ports.infrastructure.EventsEmitter;

@Log
public class ConsoleEventsEmitter implements EventsEmitter {

    @Override
    public void emit(ArticleEvent articleEvent) {
        log.info("New article event: " + articleEvent);
    }

}
