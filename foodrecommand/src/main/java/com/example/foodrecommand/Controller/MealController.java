package com.example.foodrecommand.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.foodrecommand.Model.Meal;
import com.example.foodrecommand.Model.Typefood;
import com.example.foodrecommand.Service.MealService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/meals")
public class MealController {
    @Autowired
    private MealService mealService;

    @GetMapping()
    public String Home(Model model) {
        List<Meal> meals = mealService.getAll();
        model.addAttribute("meals", meals);
        return "Meal/tables";
    }

    @GetMapping("/add")
    public String Add(Model model) {
        model.addAttribute("meal", new Meal());
        model.addAttribute("title", "Add meal");
        return "Meal/add";
    }

    @PostMapping("/add")
    public String Add(@Valid @ModelAttribute("meal") Meal meal, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("meal", meal);
            model.addAttribute("title", "Add typefood");
            return "Meal/add";
        }

        mealService.Add(meal);
        return "redirect:/typefoods";
    }

    @GetMapping("/edit/{id}")
    public String Edit(@PathVariable("id") Long id, Model model) {
        // model.addAttribute("title", "Edit Book");
        Meal meal = mealService.getbyId(id);
        if (meal != null) {
            model.addAttribute("meal", meal);
            return "Meal/edit";

        } else {
            return "not-found";
        }
    }

    @PostMapping("/edit/{id}")
    public String editUnit(@PathVariable("id") Long id, @ModelAttribute("meal") Meal meal,BindingResult bindingResult,Model model) {
        mealService.Add(meal);
        return "redirect:/meals";
    }

     
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        mealService.deletebyid(id);
        return "redirect:/meals";
    }

}
