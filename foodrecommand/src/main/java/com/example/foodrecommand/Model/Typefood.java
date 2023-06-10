package com.example.foodrecommand.Model;
import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "typefood")
public class Typefood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long idTypefood;

    @Column(name = "name")
    private String Name;

    @OneToMany(mappedBy = "typefood",cascade = CascadeType.ALL)
    private List<Food> foods;
    
}
