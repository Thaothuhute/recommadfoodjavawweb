package com.example.foodrecommand.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.foodrecommand.Model.DetailNutriFood;
import com.example.foodrecommand.Model.Unit;
import com.example.foodrecommand.Repository.IDetailNutriFoodRepository;

@Service
public class DetailNutriFoodService {
    @Autowired
    private IDetailNutriFoodRepository detailNutriFoodRepository;

     public List<DetailNutriFood> getAll(){
        return detailNutriFoodRepository.findAll();
    }
    public DetailNutriFood getbyId(Long id ){
        return detailNutriFoodRepository.findById(id).orElse(null);
    }

    public void Add(DetailNutriFood unit){
        detailNutriFoodRepository.save(unit);
    }

    public void Delete(DetailNutriFood unit)
    {
        detailNutriFoodRepository.delete(unit);
    }

    public void deleteId(Long id){
        detailNutriFoodRepository.deleteById(id);
    }

    public List<DetailNutriFood> getDNByidnutri(long id){
        return detailNutriFoodRepository.getListDNbyNutriID(id);
    }
}
