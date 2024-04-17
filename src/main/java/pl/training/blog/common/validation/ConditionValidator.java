package pl.training.blog.common.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;
import java.util.function.Predicate;

@RequiredArgsConstructor
public class ConditionValidator implements ConstraintValidator<Condition, String>/*, ApplicationContextAware*/ {

    private final Map<String, Predicate<String>> predicates;
    private ApplicationContext applicationContext;
    private Predicate<String> predicate;

    @Override
    public void initialize(Condition condition) {
        // predicate = applicationContext.getBean(condition.value(), Predicate.class);
        predicate = predicates.get(condition.value());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return predicate.test(value);
    }

    /*@Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }*/

}
