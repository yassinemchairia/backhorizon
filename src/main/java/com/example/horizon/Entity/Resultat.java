package com.example.horizon.Entity;
import java.io.Serializable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resultat implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRes;

    private String nom;
    @OneToMany(mappedBy = "resultat", cascade = CascadeType.ALL)
    private List<Commande> commandes;
}
