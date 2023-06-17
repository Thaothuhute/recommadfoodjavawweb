package com.example.foodrecommand.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import com.example.foodrecommand.Model.Food;
import com.example.foodrecommand.Repository.ITypefoodRepository;
import com.example.foodrecommand.Repository.IUnitRepository;
import com.example.foodrecommand.Service.FoodService;
import com.example.foodrecommand.Service.MealService;
import com.example.foodrecommand.Service.TypeService;
import com.example.foodrecommand.Service.UnitService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/foods")
public class FoodController {
    @Autowired
    private FoodService foodService;

    @Autowired
    private UnitService unitService;
    @Autowired
    private TypeService typeService;

    @Autowired
    private MealService mealService;
    @Autowired
    private IUnitRepository iUnitRepository;

    @GetMapping
    public String showAllFood(Model model) {
        List<Food> foods = foodService.getAllFood();
        model.addAttribute("foods", foods);
        model.addAttribute("tilte", "Danh sach do an");
        return "Food/tables";
    }

    @GetMapping("/add")
    public String Addfood(Model model) {
        model.addAttribute("food", new Food());
        model.addAttribute("unitfood", unitService.getAllUnit());
        model.addAttribute("meal", mealService.getAll());
        model.addAttribute("typefood", typeService.getAllTypeFood());
        model.addAttribute("title", "Add Book");
        return "Food/add";
    }

    @PostMapping("/add")
    public String Addfood(@Valid @ModelAttribute("food") Food food, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("food", food);
            model.addAttribute("unitfood", unitService.getAllUnit());
            model.addAttribute("meal", mealService.getAll());
            model.addAttribute("typefood", typeService.getAllTypeFood());
            return "Food/add";
        }

        foodService.AddFood(food);
        return "redirect:/foods";
    }

    @GetMapping("/edit/{id}")
    public String Editfood(@PathVariable("id") Long id, Model model) {
        model.addAttribute("unitfood", unitService.getAllUnit());
        model.addAttribute("meal", mealService.getAll());
        model.addAttribute("typefood", typeService.getAllTypeFood());

        Food foodedit = foodService.getbyIdFood(id);
        String filename = "" + id + ".jpg";

        Path imageFood = Path.of(
                "D:\\tesst\\recommadfoodjavawweb\\foodrecommand\\src\\main\\resources\\static\\assets\\img\\imgFood",
                filename);

        if (Files.exists(imageFood)) {
            model.addAttribute("imageFoodurl",
                    "../../assets/img/imgFood/" + filename);
        } else {
            model.addAttribute("imageFoodurl",
                    "../../assets/img/imgFood/nothing.jpg");
        }

        model.addAttribute("title", "Add Book");

        if (foodedit != null) {
            model.addAttribute("food", foodedit);
            return "Food/edit";
        } else {
            return "not-found";
        }

    }

    @PostMapping("/edit/{id}")
    public String editBook(@PathVariable("id") Long id, @Valid @ModelAttribute("food") Food food,
            BindingResult bindingResult, Model model, @RequestParam("file") MultipartFile file) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("unitfood", unitService.getAllUnit());
            model.addAttribute("meal", mealService.getAll());
            model.addAttribute("typefood", typeService.getAllTypeFood());
            model.addAttribute("food", food);

            return "Food/edit";
        }
        String filename = "" + id + ".jpg";

        Path imageFood = Path.of(
                "D:\\tesst\\recommadfoodjavawweb\\foodrecommand\\src\\main\\resources\\static\\assets\\img\\imgFood",
                filename);

        if (Files.exists(imageFood)) {
            model.addAttribute("imageFoodurl",
                    "../../assets/img/imgFood/" + filename);
        } else {
            model.addAttribute("imageFoodurl",
                    "../../assets/img/imgFood/nothing.jpg");
        }

        try {
            // Normalize file name
            String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());
            String fileName = "" + id + ".jpg";

            // Copy file to the target location
            Path targetLocation = Path.of(
                    "D:\\tesst\\recommadfoodjavawweb\\foodrecommand\\src\\main\\resources\\static\\assets\\img\\imgFood",
                    fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            // Redirect to a success page

        } catch (IOException e) {
            // Handle file save failure
            return "redirect:/foods";
        }
        foodService.AddFood(food);
        return "redirect:/foods";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        foodService.deteleFood(id);
        return "redirect:/foods";
    }

    @GetMapping("detail")
    public String detailfood(Model model){
        return "Food/detail";
    }
}
