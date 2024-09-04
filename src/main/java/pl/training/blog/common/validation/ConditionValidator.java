package pl.training.blog.common.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.lang.String.join;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toSet;

@RequiredArgsConstructor
public class ConditionValidator implements ConstraintValidator<Condition, String>/*, ApplicationContextAware*/ {

    private static final String DELIMITER = ", ";

    private final Map<String, Predicate<String>> predicates;

    private Set<String> predicateNames;

    @Override
    public void initialize(Condition condition) {
        predicateNames = Arrays.stream(condition.value()).collect(toSet());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        var errors = new ArrayList<String>();
        for (String predicateName : predicateNames) {
            var predicate = predicates.get(predicateName);
            if (!predicate.test(value)) {
                errors.add(predicateName);
            }
        }

        /*var errors = predicateNames.stream()
                        .reduce(emptyList(), (acc, predicateName) -> !test(value, predicateName) ? List.of(predicateName) : emptyList(), this::combine);*/

        errors.forEach(error -> context.buildConstraintViolationWithTemplate("failed predicates: " + join(DELIMITER, error))
                .addConstraintViolation());

        return errors.isEmpty();
    }

    private boolean test(String value, String predicateName) {
        return predicates.get(predicateName).test(value);
    }

    private List<String> combine(List<String> list, List<String> otherList) {
        return Stream.concat(list.stream(), otherList.stream()).toList();
    }

    /*@Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    }*/

}
