package pl.training.blog.adapters.infrastructure.events;

import lombok.extern.java.Log;
import org.springframework.stereotype.Component;
import pl.training.blog.application.ArticleEvent;
import pl.training.blog.ports.infrastructure.EventsEmitter;

@Component
@Log
public class ConsoleEventsEmitter implements EventsEmitter {

    @Override
    public void emit(ArticleEvent articleEvent) {
        log.info("New article event: " + articleEvent);
    }

}
