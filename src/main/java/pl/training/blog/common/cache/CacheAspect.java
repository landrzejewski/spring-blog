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
import java.util.function.Function;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;

@Aspect
@Component
@Log
public class CacheAspect {

    private final Map<String, Cache<String, Object>> caches = new ConcurrentHashMap<>();
    @Setter
    private Function<Integer, Cache<String, Object>> cacheSupplier = LinkedHashMapCache::new;

    @Around("@annotation(fromCache)")
    public Object read(ProceedingJoinPoint joinPoint, FromCache fromCache) throws Throwable {
        var cacheName = fromCache.value();
        var key = generateKey(cacheName, joinPoint);
        caches.putIfAbsent(cacheName, cacheSupplier.apply(fromCache.capacity()));
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
