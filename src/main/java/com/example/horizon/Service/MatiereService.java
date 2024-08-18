package com.example.horizon.Service;

import com.example.horizon.DTO.MatiereDTO;
import com.example.horizon.Entity.Matiere;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.horizon.Repository.MatiereRepository;
@Service
@Slf4j
@AllArgsConstructor
public class MatiereService {
    @Autowired
    private MatiereRepository matiereRepository;

    public Matiere addMatiere(MatiereDTO matiereDTO) {
        Matiere matiere = new Matiere();
        matiere.setCouleur(matiereDTO.getCouleur());
        matiere.setRefLabo(matiereDTO.getRefLabo());
        matiere.setPoids(matiereDTO.getPoids());
        matiere.setNbPMetr(matiereDTO.getNbPMetr());
        return matiereRepository.save(matiere);
    }
}
