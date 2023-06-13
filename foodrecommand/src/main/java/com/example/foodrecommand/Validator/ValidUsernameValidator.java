package com.example.foodrecommand.Validator;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.foodrecommand.Repository.IUnitRepository;
import com.example.foodrecommand.Repository.IUserrepository;
import com.example.foodrecommand.Validator.annotrion.ValidUsername;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Valid;

public class    ValidUsernameValidator  implements ConstraintValidator<ValidUsername,String>{
    @Autowired
    private IUserrepository uRepository;

    @Override
    public boolean isValid(String  username, ConstraintValidatorContext context) {
        if(uRepository ==null)
            return true;
        return uRepository.findByUsername(username) == null;    
    }
}
