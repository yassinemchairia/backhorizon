package com.example.horizon.Entity;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Livraison implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idP;
    private Date dateLivraison;
    private Integer nbLivree;
    private String noBL;
    @OneToMany(mappedBy = "livraison", cascade = CascadeType.ALL)
    private List<Commande> commandes;
}
