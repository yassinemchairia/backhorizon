package com.example.horizon.Entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Commande implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCom;
    private String noBC;
    private Date dateBC;
    private String noMise;
    private Date dateLancement;
    @Enumerated(EnumType.STRING)
    private SeparationTraitement separationTraitement;

    @Enumerated(EnumType.STRING)
    private LivraisonStatus livraisonStatus;

    @Enumerated(EnumType.STRING)
    private ResultatStatus resultatStatus;

    @Enumerated(EnumType.STRING)
    private MiseStatus miseStatus;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonIgnoreProperties("commandes")
    private Client client;

    @ManyToMany
    @JoinTable(
            name = "commande_matiere",
            joinColumns = @JoinColumn(name = "commande_id"),
            inverseJoinColumns = @JoinColumn(name = "matiere_id")
    )
    private List<Matiere> matieres = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "livraison_id")
    private Livraison livraison;

    @ManyToOne
    @JoinColumn(name = "resultat_id")
    private Resultat resultat;



    // Nouveaux attributs

}
