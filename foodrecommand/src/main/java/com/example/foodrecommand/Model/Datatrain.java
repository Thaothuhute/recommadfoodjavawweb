package com.example.foodrecommand.Model;

import jakarta.persistence.Entity;
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
@Table(name = "datatrain")
public class Datatrain {
    @Id
    private long id;
    private int age;
    private int active;
    private int color;
    private int keyemotion;
    private int keytaste;
}
