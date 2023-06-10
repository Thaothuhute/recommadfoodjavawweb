package com.example.foodrecommand.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.foodrecommand.Model.Unit;

public interface IUnitRepository extends JpaRepository<Unit,Long> {
    
}
