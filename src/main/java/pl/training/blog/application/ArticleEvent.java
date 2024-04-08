package pl.training.blog.application;

import java.util.UUID;

public record ArticleEvent(String type, UUID articleUuid) {
}
