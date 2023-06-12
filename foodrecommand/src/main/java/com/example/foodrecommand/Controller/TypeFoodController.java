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

import com.example.foodrecommand.Model.Typefood;
import com.example.foodrecommand.Model.Unit;
import com.example.foodrecommand.Service.TypeService;

import jakarta.validation.Valid;


@RequestMapping("/typefoods")
@Controller
public class TypeFoodController {
    @Autowired
    private  TypeService typeService;

    @GetMapping()
    public String Home(Model model) {
        List<Typefood> typefoods = typeService.getAllTypeFood();
        model.addAttribute("typefoods", typefoods);
        return "typefood/tables";
    }

    @GetMapping("/add")
    public String Add(Model model) {
        model.addAttribute("typefood", new Typefood());
        model.addAttribute("title", "Add typefood");
        return "typefood/add";
    }

    @PostMapping("/add")
    public String Add(@Valid @ModelAttribute("typefood") Typefood typefood, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("typefood", typefood);
            model.addAttribute("title", "Add typefood");
            return "typefood/add";
        }

        typeService.AddTypeFood(typefood);
        return "redirect:/typefoods";
    }

    @GetMapping("/edit/{id}")
    public String Edit(@PathVariable("id") Long id, Model model) {
        // model.addAttribute("title", "Edit Book");
        Typefood typefood = typeService.gettTypefoodbyId(id);
        if (typefood != null) {
            model.addAttribute("typefood", typefood);
            return "typefood/edit";

        } else {
            return "not-found";
        }
    }

    @PostMapping("/edit/{id}")
    public String editUnit(@PathVariable("id") Long id, @ModelAttribute("typefood") Typefood typefood,BindingResult bindingResult,Model model) {
        typeService.AddTypeFood(typefood);
        return "redirect:/typefoods";
    }

     
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        typeService.deleteTypefood(id);
        return "redirect:/typefoods";
    }

}
