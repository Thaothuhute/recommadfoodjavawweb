package com.example.foodrecommand.Model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    

    

}
