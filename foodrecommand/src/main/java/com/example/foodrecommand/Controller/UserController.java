package com.example.foodrecommand.Controller;

import java.lang.ref.Cleaner.Cleanable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.hibernate.mapping.Map;
import org.springframework.aop.framework.adapter.GlobalAdvisorAdapterRegistry;
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
import com.example.foodrecommand.Model.Aswers;
import com.example.foodrecommand.Model.Diet;
import com.example.foodrecommand.Model.Food;
import com.example.foodrecommand.Model.QuestionAnswer;
import com.example.foodrecommand.Model.Unit;
import com.example.foodrecommand.Model.User;
import com.example.foodrecommand.Service.DietService;
import com.example.foodrecommand.Service.FoodService;
import com.example.foodrecommand.Service.QandAService;
import com.example.foodrecommand.Service.UserService;
import com.example.foodrecommand.Utils.SercurityConfig;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private QandAService qandAService;
    @Autowired
    private DietService dietService;
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

    public User getcurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User usercurrent = userService.finduserbyname(username);
        return usercurrent;
    }

    @GetMapping("/user")
    public String userpo(Model model) {
        User usercurrent = getcurrentUser();
        BMR bmr = new BMR();
        Float cbmr = bmr.calosuiwAim(usercurrent.getGender(), usercurrent.getHeight(), usercurrent.getWeigh(),
                usercurrent.getAge(), usercurrent.getActive());
        IBM ibm = new IBM(usercurrent.getWeigh(), usercurrent.getHeight());

        model.addAttribute("user", usercurrent);
        model.addAttribute("ibm", ibm.coIBM());
        model.addAttribute("comibm", ibm.comIBM());

        model.addAttribute("bmr", cbmr);
        return "user/profile";
    }

    @PostMapping("/user/edit/{id}")
    public String editUnit(@PathVariable("id") Long id, @ModelAttribute("user") User user, BindingResult bindingResult,
            Model model) {

        User useredit = userService.finduserbyId(user.getUserid());
        useredit.setAge(0);
        userService.addUser(user);
        User usercurrent = user;
        BMR bmr = new BMR();
        Float cbmr = bmr.calosuiwAim(usercurrent.getGender(), usercurrent.getHeight(), usercurrent.getWeigh(),
                usercurrent.getAge(), usercurrent.getActive());
        IBM ibm = new IBM(usercurrent.getWeigh(), usercurrent.getHeight());

        model.addAttribute("user", usercurrent);
        model.addAttribute("ibm", ibm.coIBM());
        model.addAttribute("comibm", ibm.comIBM());

        model.addAttribute("bmr", cbmr);
        return "redirect:/user";
    }

    @GetMapping("/user/edit/{id}")
    public String editUser(@PathVariable("id") Long id, Model mode) {
        User useredit = userService.finduserbyId(id);
        mode.addAttribute("user", useredit);

        return "user/edit";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
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

    public List<Float> caloneedforOneday(User user) {
        BMR bmr = new BMR();
        Float caloriesneed = bmr.TDEE(user.getGender(), user.getHeight(), user.getWeigh(), user.getAge(), user.getAim(),
                user.getActive());
        List<Float> ClEachBref = new ArrayList<>();
        ClEachBref.add(caloriesneed * 30 / (float) 100);
        ClEachBref.add(caloriesneed * 40 / (float) 100);
        ClEachBref.add(caloriesneed * 30 / (float) 100);
        return ClEachBref;
    }
    public List<HashMap<Integer,Float>> updateCaloNeed (List<Float> CaloForOneDay){
        List<HashMap<Integer,Float>> CaloUpdate = new ArrayList<>();
        HashMap<Integer,Float> CaloNeedBre = new HashMap<>();
        CaloNeedBre.put(1, CaloForOneDay.get(0)*70/100);
         CaloNeedBre.put(2, CaloForOneDay.get(0)*30/100);
        CaloUpdate.add(CaloNeedBre);
         CaloNeedBre.put(1, CaloForOneDay.get(1)*70/100);
         CaloNeedBre.put(2, CaloForOneDay.get(1)*30/100);
        CaloUpdate.add(CaloNeedBre);
         CaloNeedBre.put(1, CaloForOneDay.get(2)*70/100);
         CaloNeedBre.put(2, CaloForOneDay.get(2)*30/100);
        CaloUpdate.add(CaloNeedBre);
        return CaloUpdate;
    }
    
    @GetMapping("/question")
    public String questionandanswer(Model model){
        HashMap<Integer,List<QuestionAnswer>> QaA = qandAService.getAllQandA();
        HashMap<String,Integer> answers = new HashMap<>();
        answers.put("GENDER", 0);
        answers.put("AGE", 1);
        answers.put("ACTIVE", 0);
        answers.put("COLOR", 1);
        answers.put("KETTASTE", 1);
        answers.put("KEYEMOTION", 1);
        answers.put("AIM", 0);
        Aswers aswers = new Aswers(answers);
        model.addAttribute("QaA", QaA);
        model.addAttribute("answers", aswers);

        return "user/quesandans";
    }   


    @PostMapping("/recommandFoodForWeek")
    public String recommandFood(Model model,@ModelAttribute("answers") Aswers answers){

       Aswers answer = answers;
        User userCurrent = getcurrentUser();
        List<Float> CaloForOneDay = caloneedforOneday(userCurrent);
        List<HashMap<Integer,Float>> CaloContribute = updateCaloNeed( CaloForOneDay);


        List<Diet> diets = new ArrayList<>();
        dietService.RemoveAllDeitofUser(userCurrent.getUserid());

        if(answer.getAnswer().get("ACTIVE") <=1){
            answer.getAnswer().put("ACTIVE",1);
        }
        else{
            answer.getAnswer().put("ACTIVE", 2);
        }

        
       



        return "redirect:/user";
    }

    
}
