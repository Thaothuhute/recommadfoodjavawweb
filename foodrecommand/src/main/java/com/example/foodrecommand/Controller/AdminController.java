package com.example.foodrecommand.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @GetMapping
    public String aboutUs(){
        return "Admin/index";        
    }

    @GetMapping("/units")
    public String unitcontroller(){
        return "Admin/tables";        
    }
}
