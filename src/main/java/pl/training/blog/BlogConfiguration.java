package pl.training.blog;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import pl.training.blog.common.cache.CacheAspect;
import pl.training.blog.common.cache.LinkedHashMapCache;

@EnableAspectJAutoProxy
@ComponentScan
@Configuration
public class BlogConfiguration {

    @Bean
    public CacheAspect cacheAspect() {
        var aspect = new CacheAspect();
        aspect.setCacheSupplier(LinkedHashMapCache::new);
        return aspect;
    }

}
