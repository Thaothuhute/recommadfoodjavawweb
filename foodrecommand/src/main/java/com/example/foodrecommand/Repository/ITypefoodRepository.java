package com.example.foodrecommand.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.foodrecommand.Model.Typefood;

public interface ITypefoodRepository  extends JpaRepository<Typefood,Long>{
    
}
