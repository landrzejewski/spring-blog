package pl.training.blog.common.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.function.Predicate;

@RequiredArgsConstructor
public class ConditionValidator implements ConstraintValidator<Condition, String>, ApplicationContextAware {

    private Predicate<String> predicate;
    private ApplicationContext applicationContext;

    @SuppressWarnings("unchecked")
    @Override
    public void initialize(Condition constraintAnnotation) {
        var predicateBeanName = constraintAnnotation.value();
        predicate = (Predicate<String>) applicationContext.getBean(predicateBeanName);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !predicate.test(value);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
