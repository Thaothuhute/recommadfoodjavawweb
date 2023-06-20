package com.example.foodrecommand.Controller;

import java.lang.ref.Cleaner.Cleanable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.xml.crypto.Data;

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
import com.example.foodrecommand.Model.Datatrain;
import com.example.foodrecommand.Model.DetailNutriFood;
import com.example.foodrecommand.Model.Diet;
import com.example.foodrecommand.Model.Food;
import com.example.foodrecommand.Model.QuestionAnswer;
import com.example.foodrecommand.Model.Unit;
import com.example.foodrecommand.Model.User;
import com.example.foodrecommand.Logic.Node;
import com.example.foodrecommand.Logic.RecommandFood;
import com.example.foodrecommand.Service.DataService;
import com.example.foodrecommand.Service.DetailNutriFoodService;
import com.example.foodrecommand.Service.DietService;
import com.example.foodrecommand.Service.FoodService;
import com.example.foodrecommand.Service.QandAService;
import com.example.foodrecommand.Service.UnitService;
import com.example.foodrecommand.Service.UserService;
import com.example.foodrecommand.Utils.SercurityConfig;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UserController {
    @Autowired
    private DataService dataService;
    @Autowired
    private UnitService unitService;
    @Autowired
    private UserService userService;
    @Autowired
    private QandAService qandAService;
    @Autowired
    private DietService dietService;
    @Autowired
    private FoodService foodService;
@Autowired
private DetailNutriFoodService detailNutriFoodService;
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
        Float cbmr = bmr.TDEE(usercurrent.getGender(), usercurrent.getHeight(), usercurrent.getWeigh(),
                usercurrent.getAge(),usercurrent.getAim(), usercurrent.getActive());
            
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

    public List<Float> ListCaloneedforOneday(User user) {
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
    @GetMapping("/foodrecommand")
    public String foodrecommanded(Model model) {
          User userCurrent = getcurrentUser();
        List<Float> CaloForOneDay = ListCaloneedforOneday(userCurrent);
        List<HashMap<Integer,Float>> CaloContribute = updateCaloNeed( CaloForOneDay);
        int IsExsit = 0;
        int dayDiet = 0;
        //lay db diet len
        HashMap<Integer,List<Diet>> FoodforServeralDay = new HashMap<>();
        List<Diet> dietForOneDay =  new ArrayList<>();
        List<Diet> dietforUser = dietService.getAll();
        if(dietforUser.stream().filter(x->x.getUser().getUserid() == userCurrent.getUserid()).count() >=21){
            List<Diet> dietforCurretnUser =  dietforUser.stream().filter(x->x.getUser().getUserid() == userCurrent.getUserid()).toList();
            IsExsit = 1;    
            dayDiet =1;
            for(int i = dietforCurretnUser.size() - 1;i >= dietforCurretnUser.size() -21; i-=3){
                    dietForOneDay = new ArrayList<>();
                    dietForOneDay.add(dietforCurretnUser.get(i));
                    dietForOneDay.add(dietforCurretnUser.get(i - 1));
                    dietForOneDay.add(dietforCurretnUser.get(i - 2));
                    FoodforServeralDay.put(dayDiet,dietForOneDay);
                    dayDiet++;
                    
            }
            model.addAttribute("FoodFor7day", FoodforServeralDay);
            model.addAttribute("CaloForServerralBref",CaloForOneDay);

        }
        else{
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
        model.addAttribute("Dvt", unitService.getAllUnit());
         model.addAttribute("IsExsit", IsExsit);
        return "user/foodrecomand";
    }
    

    @PostMapping("/recommandFoodForWeek")
    public String recommandFood(Model model,@ModelAttribute("answers") Aswers answers){

       Aswers answer = answers;
        User userCurrent = getcurrentUser();
        List<Float> CaloForOneDay = ListCaloneedforOneday(userCurrent);
        List<HashMap<Integer,Float>> CaloContribute = updateCaloNeed( CaloForOneDay);
        userCurrent.setAim(answer.getAnswer().get("AIM"));
        userCurrent.setActive(answer.getAnswer().get("ACTIVE"));
        userService.addUser(userCurrent);
        dietService.RemoveAllDeitofUser(userCurrent.getUserid());

        if(answer.getAnswer().get("ACTIVE") <=1){
            answer.getAnswer().put("ACTIVE",1);
        }
        else{
            answer.getAnswer().put("ACTIVE", 2);
        }

        Aswers aswersfinal = new Aswers();
        aswersfinal.setAnswer(new HashMap<>());
        aswersfinal.getAnswer().put("GENDER", answer.getAnswer().get("GENDER"));
        aswersfinal.getAnswer().put("AGE", answer.getAnswer().get("AGE"));

        aswersfinal.getAnswer().put("GENDER", answer.getAnswer().get("GENDER"));

        aswersfinal.getAnswer().put("ACTIVE", answer.getAnswer().get("ACTIVE"));

        aswersfinal.getAnswer().put("COLOR", answer.getAnswer().get("COLOR"));

        aswersfinal.getAnswer().put("KEYTASTE", answer.getAnswer().get("KEYTASTE"));
        aswersfinal.getAnswer().put("KEYEMOTION", answer.getAnswer().get("KEYEMOTION"));
        
        List<Datatrain> dbTrain = dataService.getAll();
        
        RecommandFood recommandFood = new RecommandFood(aswersfinal.getAnswer(),dbTrain);
        Node nodeRecommand = recommandFood.returnTrain();
        
        List<Diet> diets = new ArrayList<>();
        List<Food> foodForOneDay = new ArrayList<>();
        List<DetailNutriFood> detailNutriFoods = new ArrayList<>();

        detailNutriFoods = detailNutriFoodService.getAll();
        long asd =8;
        long idnutrifood =  (long) nodeRecommand.Label.get(0);
        detailNutriFoods = detailNutriFoods.stream().filter(x->x.getNutribute().getIdNutribute() == idnutrifood).toList();


        for(int i =0;i<7;i++){
            foodForOneDay = new ArrayList<>();
            List<Integer> ListRandom = new ArrayList<>();
            for(int j=0;j<3;j++  ){
                Random random = new Random();
                int randomNumber = random.nextInt(detailNutriFoods.size()-1-0+1)-0;
                // if(ListRandom.stream().filter(x->x==randomNumber).count() !=0){
                //     j--;
                // }
                // else{
                //     ListRandom.add(randomNumber);
                    Food food= detailNutriFoods.get(randomNumber).getFood();
                    foodForOneDay.add(food);
                }
            
            for (Food item : foodForOneDay) {
                Diet bref1 = new Diet();
                bref1.setUser(userCurrent);
                bref1.setFood(item);
                LocalDate datecurrent = LocalDate.now();
                bref1.setDay(Date.valueOf(datecurrent));
                bref1.setMeal(item.getMeal());
                dietService.Add(bref1);
            }
        }

        int IsExsit = 0;
        int dayDiet = 0;
        //lay db diet len
        HashMap<Integer,List<Diet>> FoodforServeralDay = new HashMap<>();
        List<Diet> dietForOneDay =  new ArrayList<>();
        List<Diet> dietforUser = dietService.getAll();
        if(dietforUser.stream().filter(x->x.getUser().getUserid() == userCurrent.getUserid()).count() >=21){
            List<Diet> dietforCurretnUser =  dietforUser.stream().filter(x->x.getUser().getUserid() == userCurrent.getUserid()).toList();
            IsExsit = 1;    
            dayDiet =1;
            for(int i = dietforCurretnUser.size() - 1;i >= dietforCurretnUser.size() -21; i-=3){
                    dietForOneDay = new ArrayList<>();
                    dietForOneDay.add(dietforCurretnUser.get(i));
                    dietForOneDay.add(dietforCurretnUser.get(i - 1));
                    dietForOneDay.add(dietforCurretnUser.get(i - 2));
                    FoodforServeralDay.put(dayDiet,dietForOneDay);
                    dayDiet++;
                    
            }
            model.addAttribute("FoodFor7day", FoodforServeralDay);
            model.addAttribute("CaloForServerralBref",CaloForOneDay);

        }
        else{
            IsExsit = 0;
        }
        model.addAttribute("Dvt", unitService.getAllUnit());
         model.addAttribute("IsExsit", IsExsit);
        return "user/foodrecomand";
    }

   
}
