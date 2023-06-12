package com.example.foodrecommand.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.foodrecommand.Model.Datatrain;
import com.example.foodrecommand.Repository.IDatatrainRepository;

@Service
public class DataService {
    @Autowired
    private IDatatrainRepository iDatatrainRepository;

    public List<Datatrain> getAll(){
        return iDatatrainRepository.findAll();
    }


    public Datatrain getbyId(Long id){
        return iDatatrainRepository.findById(id).orElse(null);
    }

    public void Add(Datatrain typefood){
        iDatatrainRepository.save(typefood);
    }

    public void deletebyid(Long id){
        iDatatrainRepository.deleteById(id);
    }

}
