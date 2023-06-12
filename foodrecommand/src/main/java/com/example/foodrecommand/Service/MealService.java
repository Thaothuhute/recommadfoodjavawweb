package com.example.foodrecommand.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.foodrecommand.Model.Meal;
import com.example.foodrecommand.Repository.IMealRepository;

@Service
public class MealService {
    @Autowired
    private IMealRepository typefoodRepository;

    public List<Meal> getAll(){
        return typefoodRepository.findAll();
    }


    public Meal getbyId(Long id){
        return typefoodRepository.findById(id).orElse(null);
    }

    public void Add(Meal typefood){
        typefoodRepository.save(typefood);
    }

    public void deletebyid(Long id){
        typefoodRepository.deleteById(id);
    }
}
