package com.example.horizon.DTO;

import com.example.horizon.Entity.LivraisonStatus;
import com.example.horizon.Entity.MiseStatus;
import com.example.horizon.Entity.SeparationTraitement;
import lombok.Data;

import java.util.List;

@Data
public class CommandeDTO {
    private String nomClient;
    private List<Long> matiereIds;
    private MiseStatus miseStatus;
    private LivraisonStatus livraisonStatus;
    private SeparationTraitement separationTraitement;;
    private String noBC;  // Champs pour noBC
    private String noMise;
}
