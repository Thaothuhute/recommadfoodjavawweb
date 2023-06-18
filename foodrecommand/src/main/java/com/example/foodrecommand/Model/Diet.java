package com.example.foodrecommand.Model;

import java.sql.Date;

import com.example.foodrecommand.Validator.annotrion.ValidTypefoodId;
import com.example.foodrecommand.Validator.annotrion.ValidUserId;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="diet")
public class Diet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    public int iddiet;

    @Column(name = "meal")
    public int meal;

    @Column(name ="day")
    public Date day;

    @ManyToOne
    @JoinColumn(name  = "idFood", referencedColumnName = "Idfood")
    private Food food;
    

    @ManyToOne
    @JoinColumn(name  = "idUser", referencedColumnName = "userid")
    @ValidUserId
    private User user;
    
    

    

}
