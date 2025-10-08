package pl.training.blog.common.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toSet;

@RequiredArgsConstructor
public class ConditionValidator implements ConstraintValidator<Condition, String> {

    private final Map<String, Predicate<String>> predicates;
    private Set<String> predicateNames;

    @Override
    public void initialize(Condition constraintAnnotation) {
        predicateNames = Arrays.stream(constraintAnnotation.value()).collect(toSet());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        var failedPredicates = new ArrayList<String>();

        for (String predicateName : predicateNames) {
            var predicate = predicates.get(predicateName);
            if (!predicate.test(value)) {
                failedPredicates.add(predicateName);
            }
        }
        failedPredicates.forEach(failedPredicate -> context.buildConstraintViolationWithTemplate(failedPredicate).addConstraintViolation());
        return failedPredicates.isEmpty();
    }

}
