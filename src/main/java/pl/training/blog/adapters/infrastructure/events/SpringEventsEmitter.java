package pl.training.blog.adapters.infrastructure.events;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import pl.training.blog.application.ArticleEvent;
import pl.training.blog.ports.infrastructure.EventsEmitter;

@RequiredArgsConstructor
public class SpringEventsEmitter implements EventsEmitter {

    private final ApplicationEventPublisher publisher;

    @Override
    public void emit(ArticleEvent articleEvent) {
        publisher.publishEvent(articleEvent);
    }

}
