package com.example.foodrecommand.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder.Default;

import javax.management.loading.MLet;

import com.example.foodrecommand.Validator.annotrion.ValidMealId;
import com.example.foodrecommand.Validator.annotrion.ValidTypefoodId;
import com.example.foodrecommand.Validator.annotrion.ValidUnitId;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "food")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long Idfood;

    @Column(name = "name")
    @NotEmpty(message = "title không được để trống")
    @Size(max = 50, min = 1, message = "Tên không vượt quá 50 ký tự")
    private String name;

    @Column(name ="calo")
    
    private int calo;
    @Column(name ="chatdam")

    private int chatdam;
    @Column(name ="chatxo")

    private int chatxo;
    @Column(name ="chatbeo")            

    private int chatbeo;
    @Column(name ="qlove")

    private int qlove;

    @ManyToOne
    @JoinColumn(name  = "typeId", referencedColumnName = "idTypefood")
    @ValidTypefoodId
    private Typefood typefood;
    
    @ManyToOne
    @JoinColumn(name  = "mealId", referencedColumnName = "idMeal")
    @ValidMealId 
    private Meal meal;

    @ManyToOne
    @JoinColumn(name  = "unitId", referencedColumnName = "idUnit")
    @ValidUnitId  
    private Unit unit;

}
