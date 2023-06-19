package com.example.foodrecommand.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.foodrecommand.Model.DetailNutriFood;

import jakarta.transaction.Transactional;

public interface IDetailNutriFoodRepository extends JpaRepository<DetailNutriFood,Long> {
    @Modifying
    @Transactional
    @Query("SELECT r FROM DetailNutriFood r WHERE r.nutribute.idNutribute = :idnutributetion")
    List<DetailNutriFood> getListDNbyNutriID(@Param("idnutributetion") Long idnutributetion);
}
