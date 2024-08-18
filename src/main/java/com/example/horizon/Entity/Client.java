package com.example.horizon.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Pour éviter les problèmes de proxy Hibernate
public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codeClient;
    private String nomClient;
    private String adresseVillePays;
    private String login;
    private String motDePasse;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("client")
    private List<Commande> commandes;
}
