package com.example.foodrecommand.Validator.annotrion;

import com.example.foodrecommand.Validator.ValidMealidValidator;
import com.example.foodrecommand.Validator.ValidTypeFoodidValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Target({ TYPE, FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = ValidMealidValidator.class)
@Documented
public @interface ValidMealId {
    String message() default "Vui lòng chọn Khoa";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
