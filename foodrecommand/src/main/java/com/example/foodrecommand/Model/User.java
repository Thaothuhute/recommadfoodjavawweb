package com.example.foodrecommand.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long userid;
    private String password;
    @Column(name = "email", length = 50)
    @NotNull(message = "email không được để trống")
    @Size(max = 50, message = "email không vượt quá 50 ký tự")
    
    private String email;
    @Column(name = "nameUser", nullable = false)
    @NotNull(message = "name không được để trống")
    private String username;

    @Column(name ="height")
    private float height;

    @Column(name ="age")
    private int age;

    @Column(name="weigh")
    private float weigh;

    @Column(name ="gender")
    private int gender;

    @Column(name="active")
    private int active;
    
    
}
