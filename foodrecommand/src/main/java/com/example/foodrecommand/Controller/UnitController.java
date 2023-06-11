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

import com.example.foodrecommand.Model.Food;
import com.example.foodrecommand.Model.Unit;
import com.example.foodrecommand.Service.UnitService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/units")
public class UnitController {
    @Autowired
    private UnitService unitService;

    @GetMapping()
    public String Homeunit(Model model) {
        List<Unit> units = unitService.getAllUnit();
        model.addAttribute("units", units);
        return "unit/tables";
    }

    @GetMapping("/add")
    public String Addfood(Model model) {
        model.addAttribute("unit", new Unit());
        model.addAttribute("title", "Add unit");
        return "unit/add";
    }

    @PostMapping("/add")
    public String addBook(@Valid @ModelAttribute("unit") Unit unit, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("unit", unit);
            model.addAttribute("title", "Add unit");
            return "unit/add";
        }

        unitService.Addunit(unit);
        return "redirect:/units";
    }

    @GetMapping("/edit/{id}")
    public String editUnitForm(@PathVariable("id") Long id, Model model) {
        // model.addAttribute("title", "Edit Book");
        Unit editunit = unitService.getUnitbyId(id);
        if (editunit != null) {
            model.addAttribute("unit", editunit);
            return "unit/edit";

        } else {
            return "not-found";
        }
    }

    @PostMapping("/edit/{id}")
    public String editUnit(@PathVariable("id") Long id, @ModelAttribute("unit") Unit unit,BindingResult bindingResult,Model model) {
       Unit un = unitService.getUnitbyId(id);
        un.setDescribeunit(unit.getDescribeunit());
        un.setNameunit(unit.getNameunit());
        unitService.Addunit(un);
        return "redirect:/units";
    }
}
