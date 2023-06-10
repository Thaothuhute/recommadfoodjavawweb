package com.example.foodrecommand.Validator;

import javax.xml.validation.Validator;

import com.example.foodrecommand.Model.Typefood;
import com.example.foodrecommand.Validator.annotrion.ValidTypefoodId;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidTypeFoodidValidator  implements ConstraintValidator<ValidTypefoodId,Typefood>{

    @Override
    public boolean isValid(Typefood typefood, ConstraintValidatorContext context) {
        return typefood != null && typefood.getIdTypefood() != 0;
    }
   
}
