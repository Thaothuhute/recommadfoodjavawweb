package com.example.foodrecommand.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.foodrecommand.Model.Datatrain;

public interface IDatatrainRepository extends JpaRepository<Datatrain,Long>{
    
}
