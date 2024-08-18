package com.example.horizon.Entity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Matiere implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idM;
    private String couleur;
    private String refLabo;
    private Integer poids;
    private Integer nbPMetr;

    @ManyToMany(mappedBy = "matieres")
    private List<Commande> commandes;

}
