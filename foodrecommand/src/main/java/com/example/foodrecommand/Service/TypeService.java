package com.example.foodrecommand.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.foodrecommand.Model.Typefood;
import com.example.foodrecommand.Repository.ITypefoodRepository;

import aj.org.objectweb.asm.TypeReference;

@Service
public class TypeService {
    @Autowired
    private ITypefoodRepository typefoodRepository;

    public List<Typefood> getAllTypeFood(){
        return typefoodRepository.findAll();
    }


    public Typefood gettTypefoodbyId(Long id){
        return typefoodRepository.findById(id).orElse(null);
    }

    public void AddTypeFood(Typefood typefood){
        typefoodRepository.save(typefood);
    }

    public void deleteTypefood(Long id){
        typefoodRepository.deleteById(id);
    }
}
