package com.example.foodrecommand.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.foodrecommand.Model.Unit;
import com.example.foodrecommand.Repository.IUnitRepository;

@Service
public class UnitService {
    @Autowired
    private IUnitRepository iUnitRepository;

    public List<Unit> getllUnit(){
        return iUnitRepository.findAll();
    }
    public Unit getUnitbyId(Long id ){
        return iUnitRepository.findById(id).orElse(null);
    }

    public void Addunit(Unit unit){
        iUnitRepository.save(unit);
    }

    public void DeleteUnit(Unit unit)
    {
        iUnitRepository.delete(unit);
    }

}