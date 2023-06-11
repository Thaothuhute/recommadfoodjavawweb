package com.example.foodrecommand.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.foodrecommand.Model.Food;
import com.example.foodrecommand.Model.Unit;
import com.example.foodrecommand.Service.UnitService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/units")
public class UnitController {
   @Autowired
   private UnitService unitService;

  
     @GetMapping("/add")
    public String Addfood(Model model) {
        model.addAttribute("unit", new Unit());
        model.addAttribute("unit", unitService.getAllUnit());
        model.addAttribute("title", "Add unit");
        return "unit/add";
    }
    @PostMapping("/add")
        public String addBook(@Valid @ModelAttribute("unit") Unit unit, BindingResult bindingResult, Model model) {
            if (bindingResult.hasErrors()) {
            model.addAttribute("unit", unitService.getAllUnit());
                model.addAttribute("unit", unit);
                model.addAttribute("title", "Add unit");
                return "unit/add";
            }

            unitService.Addunit(unit);
            return "redirect:/units";
        }
    
}
