package com.example.foodrecommand.Model;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.example.foodrecommand.Validator.annotrion.ValidUsername;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@AllArgsConstructor

@Entity
@Table(name = "user")
public class User {
   @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long userid;

    @Column(name = "password", length = 205, nullable = false)
    @NotEmpty(message = "password không được để trống")
    private String password;
    @Column(name = "email", length = 50)
    @NotNull(message = "email không được để trống")
    @Size(max = 50, message = "email không vượt quá 50 ký tự")
    

    
    private String email;
   @Column(name = "username", length = 50, nullable = false, unique = true)
    @NotEmpty(message = "username không được để trống")
    @Size(max = 50, message = "Tên không vượt quá 50 ký tự")
    @ValidUsername
    private String username;

    @Column(name ="height")
    private int height;

    @Column(name ="age")
    private int age;

    @Column(name="weigh")
    private int weigh;

    @Column(name ="gender")
    private int gender;

    @Column(name="active")
    private int active;
    @Fetch(FetchMode.JOIN)
    @ManyToMany()
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User(){}
    
}
