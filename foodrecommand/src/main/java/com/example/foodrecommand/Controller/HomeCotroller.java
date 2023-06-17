package com.example.foodrecommand.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.foodrecommand.Model.Food;
import com.example.foodrecommand.Service.FoodService;

@Controller
@RequestMapping("")
public class HomeCotroller{
    @Autowired
    private FoodService foodService;

    @GetMapping("/home")
    public String home(){
        
        return "Home/layout";
    }
    @GetMapping("/menu")
    public String getMenu(Model model){
         List<Food> foods = foodService.getAllFood();
        model.addAttribute("foods", foods);
        model.addAttribute("tilte", "Danh sach do an");
        return "Home/menu"; 
    }
    @GetMapping("/about")
    public String aboutUs(){
        return "Home/about";        
    }
     @GetMapping("/event")
    public String event(){
        return "Home/event";        
    }
    @GetMapping("/detailfood/{id}")
    public String Editfood (@PathVariable("id") Long id, Model model){
        Food food = foodService.getbyIdFood(id);
        model.addAttribute("food", food);
        return "home/detailFood";
    }
   
   
}