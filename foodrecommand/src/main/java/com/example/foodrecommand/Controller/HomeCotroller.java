package com.example.foodrecommand.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class HomeCotroller{
    @GetMapping
    public String home(){
        
        return "Home/layout";
    }

    @GetMapping("/about")
    public String aboutUs(){
        return "Home/about";        
    }
     @GetMapping("/event")
    public String event(){
        return "Home/event";        
    }
   
}