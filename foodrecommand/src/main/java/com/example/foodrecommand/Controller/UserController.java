package com.example.foodrecommand.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.validation.Valid;

import com.example.foodrecommand.Logic.BMR;
import com.example.foodrecommand.Logic.IBM;
import com.example.foodrecommand.Model.Food;
import com.example.foodrecommand.Model.Unit;
import com.example.foodrecommand.Model.User;
import com.example.foodrecommand.Service.FoodService;
import com.example.foodrecommand.Service.UserService;
import com.example.foodrecommand.Utils.SercurityConfig;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;



@Controller
public class UserController {
     @Autowired
    private UserService  userService;


    @Autowired
    private FoodService foodService;
    @GetMapping("/login")
    public String login(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
                return "redirect:/menu";
        }
        return "user/login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        User uswUser = new User();
        
        Food sFood = new Food();

        model.addAttribute("user", uswUser);
        return "user/register";
    }
    @GetMapping("/user")
    public String userpo(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User usercurrent = userService.finduserbyname(username);
        BMR bmr = new BMR();
        Float cbmr = bmr.calosuiwAim(usercurrent.getGender(), usercurrent.getHeight(), usercurrent.getWeigh(), usercurrent.getAge(), usercurrent.getActive());
        IBM ibm = new IBM(usercurrent.getWeigh(),usercurrent.getHeight());

        model.addAttribute("user",usercurrent);
        model.addAttribute("ibm", ibm.coIBM());
        model.addAttribute("comibm", ibm.comIBM());

        
        model.addAttribute("bmr", cbmr);
        return "user/profile";
    }
    @PostMapping("/user/edit/{id}")
   public String editUnit(@PathVariable("id") Long id, @ModelAttribute("user") User user,BindingResult bindingResult,Model model) {
        
        User useredit = userService.finduserbyId(user.getUserid());
        useredit.setAge(0);
        userService.addUser(user);
         User usercurrent = user;
        BMR bmr = new BMR();
        Float cbmr = bmr.calosuiwAim(usercurrent.getGender(), usercurrent.getHeight(), usercurrent.getWeigh(), usercurrent.getAge(), usercurrent.getActive());
        IBM ibm = new IBM(usercurrent.getWeigh(),usercurrent.getHeight());

        model.addAttribute("user",usercurrent);
        model.addAttribute("ibm", ibm.coIBM());
        model.addAttribute("comibm", ibm.comIBM());

        
        model.addAttribute("bmr", cbmr);
        return "redirect:/user";
    }
    @GetMapping("/user/edit/{id}")
     public String editUser(@PathVariable ("id") Long id,Model mode){
        User useredit = userService.finduserbyId(id);
        mode.addAttribute("user", useredit);
       
       return "user/edit";
    }
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") User user,BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                model.addAttribute(error.getField() + "_error",
                        error.getDefaultMessage());
            }
            return "redirect:/menu";
        }
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setGender(0);
        user.setActive(0);
        user.setWeigh(0);
        userService.save(user);
        return "redirect:/login";
    }
}
