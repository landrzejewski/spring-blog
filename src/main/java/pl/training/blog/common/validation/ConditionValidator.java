package pl.training.blog.common.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.function.Predicate;

@RequiredArgsConstructor
public class ConditionValidator implements ConstraintValidator<Condition, String> {

    private Predicate<String> predicate;
    private final Map<String, Predicate<String>> predicates;

    @Override
    public void initialize(Condition constraintAnnotation) {
        predicate = predicates.get(constraintAnnotation.value());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return predicate.test(value);
    }

}
