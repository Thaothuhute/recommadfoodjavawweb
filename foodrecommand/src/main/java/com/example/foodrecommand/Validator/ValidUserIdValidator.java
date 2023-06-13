package com.example.foodrecommand.Validator;

import com.example.foodrecommand.Model.User;
import com.example.foodrecommand.Validator.annotrion.ValidUserId;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidUserIdValidator  implements ConstraintValidator<ValidUserId, User> {
    @Override
    public boolean isValid(User user, ConstraintValidatorContext context) {
          if(user==null)
        return true;
        return user.getUserid() != null;
    }
}
