// package com.example.foodrecommand.Validator.annotrion;

// import jakarta.validation.Constraint;
// import jakarta.validation.Payload;
// import java.lang.annotation.Documented;
// import java.lang.annotation.Retention;
// import java.lang.annotation.Target;

// import com.example.foodrecommand.Validator.ValidFoodIdValidator;

// import static java.lang.annotation.ElementType.FIELD;
// import static java.lang.annotation.ElementType.TYPE;
// import static java.lang.annotation.RetentionPolicy.RUNTIME;



// @Target({ TYPE, FIELD })
// @Retention(RUNTIME)
// @Constraint(validatedBy = ValidFoodIdValidator.class)
// @Documented
// public @interface ValidFoodId {
//     String message() default "Vui lòng chọn mon";
//     Class<?>[] groups() default {};
//     Class<? extends Payload>[] payload() default {};
// }
