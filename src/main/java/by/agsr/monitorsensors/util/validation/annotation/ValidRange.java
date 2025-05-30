package by.agsr.monitorsensors.util.validation.annotation;

import by.agsr.monitorsensors.util.validation.constraint.RangeConstraintValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.TYPE)
@Constraint(validatedBy = RangeConstraintValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidRange {
    String message() default "Нижнее значение диапазона должно быть меньше верхнего значения диапазона";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
