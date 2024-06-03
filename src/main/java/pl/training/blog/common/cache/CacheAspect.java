package pl.training.blog.common.cache;

import lombok.Setter;
import lombok.extern.java.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;

@Aspect
@Component
@Log
public class CacheAspect {

    private static final int DEFAULT_CACHE_CAPACITY = 10;

    private final Map<String, Cache<String, Object>> caches = new ConcurrentHashMap<>();
    private final int capacity;
    @Setter
    private Supplier<Cache<String, Object>> cacheSupplier = () -> new LinkedHashMapCache<>(DEFAULT_CACHE_CAPACITY);

    public CacheAspect(int capacity) {
        this.capacity = capacity;
    }

    public CacheAspect() {
        capacity = DEFAULT_CACHE_CAPACITY;
    }

    @Around("@annotation(fromCache)")
    public Object read(ProceedingJoinPoint joinPoint, FromCache fromCache) throws Throwable {
        var cacheName = fromCache.value();
        var key = generateKey(cacheName, joinPoint);
        caches.putIfAbsent(cacheName, cacheSupplier.get());
        var cache = caches.get(cacheName);
        var value = cache.get(key);
        if (value.isPresent()) {
            log.info("Cache hit");
            return value.get();
        }
        var result = joinPoint.proceed();
        cache.put(key, result);
        return result;
    }

    private String generateKey(String cacheName, JoinPoint joinPoint) {
        return cacheName + stream(joinPoint.getArgs()).map(Object::toString).collect(joining());
    }

}
