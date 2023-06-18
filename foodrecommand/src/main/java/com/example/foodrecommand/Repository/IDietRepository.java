package com.example.foodrecommand.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.foodrecommand.Model.Datatrain;
import com.example.foodrecommand.Model.Diet;

import jakarta.transaction.Transactional;


public interface IDietRepository extends JpaRepository<Diet,Long> {
    @Modifying
    @Transactional
    @Query("DELETE  FROM Diet r WHERE r.user.userid = :userid")
    void DeleteDietbyUserid(@Param("userid") Long Userid);
}
