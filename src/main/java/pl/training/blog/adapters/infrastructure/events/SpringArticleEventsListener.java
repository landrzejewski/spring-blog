package pl.training.blog.adapters.infrastructure.events;

import lombok.extern.java.Log;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import pl.training.blog.application.ArticleEvent;

@Log
public class SpringArticleEventsListener {

    @Async
    @EventListener
    public void onArticleLiked(ArticleEvent articleEvent) {
        log.info("Article liked: " + articleEvent);
    }

}
