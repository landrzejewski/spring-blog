package pl.training.blog.common.cache;

import lombok.extern.java.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;

@Aspect
@Component
@Log
public class CacheAspect {

    private final Map<String, LinkedHashMapCache<String, Object>> caches = new ConcurrentHashMap<>();
    private final int capacity;

    public CacheAspect(int capacity) {
        this.capacity = capacity;
        caches.put("", new LinkedHashMapCache<>(capacity));
    }

    public CacheAspect() {
        capacity = 10;
    }

    @Around("@annotation(fromCache)")
    public Object readFromCache(ProceedingJoinPoint joinPoint, FromCache fromCache) throws Throwable {
        var cacheName = fromCache.value();
        var key = generateKey(cacheName, joinPoint);
        caches.putIfAbsent(cacheName, new LinkedHashMapCache<>(capacity));
        var cache = caches.get(cacheName);
        var value = cache.get(key);
        if (value.isPresent()) {
            log.info("Cache hit");
            return cache.get(key).get();
        }
        var result = joinPoint.proceed();
        cache.put(key, result);
        return result;
    }

    private String generateKey(String cacheName, JoinPoint joinPoint) {
        return cacheName + stream(joinPoint.getArgs()).map(Object::toString).collect(joining());
    }

}
