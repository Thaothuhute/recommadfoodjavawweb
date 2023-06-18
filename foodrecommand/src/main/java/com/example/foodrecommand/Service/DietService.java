package com.example.foodrecommand.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.foodrecommand.Model.Diet;
import com.example.foodrecommand.Model.Meal;
import com.example.foodrecommand.Repository.IDietRepository;

@Service
public class DietService {
    @Autowired
    private IDietRepository dietRepository;

    public void RemoveAllDeitofUser(Long iduser){
        dietRepository.DeleteDietbyUserid(iduser);
    }

    public List<Diet> getAll(){
        return dietRepository.findAll();
    }


    public Diet getbyId(Long id){
        return dietRepository.findById(id).orElse(null);
    }

    public void Add(Diet diet){
        dietRepository.save(diet);
    }

    public void deletebyid(Long id){
        dietRepository.deleteById(id);
    }
}
