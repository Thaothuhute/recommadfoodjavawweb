package com.example.foodrecommand.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.foodrecommand.Model.Food;

public interface IFoodRepository  extends JpaRepository<Food,Long>{
    
}
