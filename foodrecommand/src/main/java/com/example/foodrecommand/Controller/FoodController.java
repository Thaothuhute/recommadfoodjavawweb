package com.example.foodrecommand.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.foodrecommand.Model.Food;
import com.example.foodrecommand.Repository.ITypefoodRepository;
import com.example.foodrecommand.Repository.IUnitRepository;
import com.example.foodrecommand.Service.FoodService;
import com.example.foodrecommand.Service.TypeService;

@Controller
@RequestMapping("/foods")
public class FoodController {
    @Autowired
    private FoodService foodService;

    @Autowired
    private TypeService typeService;
    @Autowired 
    private IUnitRepository iUnitRepository;
    @GetMapping
    public String showAllFood(Model model){
        List<Food> foods = foodService.getAllFood();
        model.addAttribute("foods", foods);
        model.addAttribute("tilte", "Danh sach do an");
        return "Food/list"; 
    }
    @GetMapping("/add")
    public String Addfood(Model model) {
        model.addAttribute("food", new Food());
        model.addAttribute("typefood", typeService.getAllTypeFood());
        model.addAttribute("title", "Add Book");
        return "book/add";
    }
    

    
}
