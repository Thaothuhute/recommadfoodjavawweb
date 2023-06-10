package com.example.foodrecommand.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.foodrecommand.Model.Food;
import com.example.foodrecommand.Repository.IFoodRepository;

@Service
public class FoodService {
    @Autowired
    private IFoodRepository foodRepository;
    public List<Food> getAllFood(){
        return foodRepository.findAll();
    }


    public Food getbyIdFood(Long idFood){
        return foodRepository.findById(idFood).orElse(null);
    }

    public void AddFood(Food food){
        foodRepository.save(food);
    }

    public void deteleFood(Long id){
        foodRepository.deleteById(id);
    }
    
}
