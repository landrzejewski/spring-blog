package pl.training.blog.common.validation;

import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component("minLength")
public class MinLengthPredicate implements Predicate<String> {

    @Setter
    private int minLength = 10;

    @Override
    public boolean test(String text) {
        return text.length() >= minLength;
    }

}
