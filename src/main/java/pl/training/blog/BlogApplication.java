package pl.training.blog;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

@SpringBootApplication
@RequiredArgsConstructor
public class BlogApplication implements ApplicationRunner {

    private final Map<String, Predicate<String>> predicateMap;

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println();
    }

}
