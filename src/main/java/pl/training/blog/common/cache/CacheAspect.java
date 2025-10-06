package pl.training.blog.common.cache;

import lombok.Setter;
import lombok.extern.java.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;

@Log
@Aspect
public class CacheAspect {

    private final Map<String, Cache<String, Object>> caches = new ConcurrentHashMap<>();
    @Setter
    private Function<Integer, Cache<String, Object>> cacheSupplier = LinkedHashMapCache::new;

    @Around("@annotation(fromCache)")
    public Object read(ProceedingJoinPoint joinPoint, FromCache fromCache) throws Throwable {
        var cacheName = fromCache.value();
        var capacity = fromCache.capacity();
        caches.putIfAbsent(cacheName, cacheSupplier.apply(capacity));
        var cache = caches.get(cacheName);
        var key = generateKey(joinPoint);
        var value = cache.get(key);
        if (value.isPresent()) {
            log.info("Cache hit");
            return value.get();
        }
        var result = joinPoint.proceed();
        cache.put(key, result);
        return result;
    }

    private String generateKey(ProceedingJoinPoint joinPoint) {
        return stream(joinPoint.getArgs())
                .map(Object::toString)
                .collect(joining());
    }

}
