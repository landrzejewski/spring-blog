package pl.training.blog.common.cache;

import lombok.extern.java.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Scope;

import java.util.Arrays;
import java.util.stream.Collectors;

@Scope("prototype")
@Aspect
@Log
public class CacheAspect {

    private final LinkedHashMapCache<String, Object> cache;

    public CacheAspect(int capacity) {
        cache = new LinkedHashMapCache<>(capacity);
    }

    @Around("@annotation(FromCache)")
    public Object readFromCache(ProceedingJoinPoint joinPoint) throws Throwable {
        var key = generateKey(joinPoint);
        var valueFromCache = cache.get(key);
        if (valueFromCache.isPresent()) {
            log.info("Cache hit");
            return valueFromCache.get();
        } else {
            var result = joinPoint.proceed();
            cache.put(key, result);
            return result;
        }
    }

    private String generateKey(JoinPoint joinPoint) {
        return joinPoint.getTarget().getClass().getName()
                + joinPoint.getSignature().getName()
                + Arrays.stream(joinPoint.getArgs())
                .map(Object::toString)
                .collect(Collectors.joining());
    }


}
