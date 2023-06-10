package com.example.foodrecommand.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.foodrecommand.Model.Meal;

public interface IMealRepository extends JpaRepository<Meal,Long> {
    
}
