package com.example.foodrecommand.Validator.annotrion;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.example.foodrecommand.Validator.ValidUserIdValidator;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
@Target({ TYPE, FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = ValidUserIdValidator.class)
@Documented
public @interface ValidUserId {
    String message() default "invalid user id ";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
