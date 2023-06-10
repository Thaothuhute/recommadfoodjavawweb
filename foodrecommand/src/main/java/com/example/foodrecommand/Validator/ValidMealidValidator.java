package com.example.foodrecommand.Validator;

import com.example.foodrecommand.Model.Meal;
import com.example.foodrecommand.Validator.annotrion.ValidMealId;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidMealidValidator implements ConstraintValidator <ValidMealId,Meal> {
    @Override
    public boolean isValid(Meal meal, ConstraintValidatorContext context) {
        return meal != null && meal.getIdMeal() != 0;
    }
}
