package com.example.foodrecommand.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.foodrecommand.Model.Datatrain;
import com.example.foodrecommand.Model.Diet;


public interface IDietRepository extends JpaRepository<Diet,Long> {
       @Query("DELETE  FROM Diet r WHERE r.user.userid = ?1")
    void DeleteDietbyUserid(Long Userid);
}
