package com.example.foodrecommand.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "nutribute")
public class Nutribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long idNutribute;

    @Column(name = "namenutri")
    private String Namenutri;

    @Column(name = "describenutri")
    private String Describe;
}
