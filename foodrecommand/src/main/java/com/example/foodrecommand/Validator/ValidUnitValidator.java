package com.example.foodrecommand.Validator;


import com.example.foodrecommand.Model.Unit;
import com.example.foodrecommand.Validator.annotrion.ValidUnitId;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidUnitValidator implements  ConstraintValidator<ValidUnitId,Unit>{

    @Override
    public boolean isValid(Unit value, ConstraintValidatorContext context) {
        // TODO Auto-generated method stub
        return value != null && value.getIdUnit() != 0;
    }


}
