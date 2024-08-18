package com.example.horizon.Service;
import com.example.horizon.Entity.*;
import com.example.horizon.Repository.ClientRepository;
import com.example.horizon.Repository.CommandeRepository;
import com.example.horizon.Repository.LivraisonRepository;
import com.example.horizon.Repository.MatiereRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor

public class CommandeService {
    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private MatiereRepository matiereRepository;
    @Autowired
    private LivraisonRepository livraisonRepository;;

    public Commande ajouterCommande(Long clientId, Long matiereId, MiseStatus miseStatus, String noBC, String noMise) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client non trouvé"));

        Matiere matiere = matiereRepository.findById(matiereId)
                .orElseThrow(() -> new RuntimeException("Matière non trouvée"));

        Commande commande = new Commande();
        commande.setClient(client);
        commande.setNoBC(noBC); // Implémenter la méthode pour générer noBC
        commande.setDateBC(new Date());
        commande.setNoMise(noMise); // Implémenter la méthode pour générer noMise
        commande.setDateLancement(new Date());
        commande.getMatieres().add(matiere); // Ajoute la matière à la liste des matières
        commande.setMiseStatus(miseStatus);// Les statuts livraisonStatus et resultatStatus sont laissés vides

        return commandeRepository.save(commande);
    }


    @Transactional
    public Commande save(Commande commande) {
        return commandeRepository.save(commande);
    }
    public Commande livrerCommande(Long idCom, Livraison livraisonDetails) {
        // Récupérer la commande par son ID
        Optional<Commande> commandeOpt = commandeRepository.findById(idCom);
        if (!commandeOpt.isPresent()) {
            throw new RuntimeException("Commande non trouvée");
        }

        Commande commande = commandeOpt.get();

        // Mettre à jour la date de lancement
        commande.setDateLancement(new Date());

        // Mettre à jour le statut de livraison et de résultat
        commande.setLivraisonStatus(LivraisonStatus.LIVREES);
        commande.setResultatStatus(ResultatStatus.SUCCEES);

        // Créer et associer la livraison
        Livraison livraison = new Livraison();
        livraison.setDateLivraison(livraisonDetails.getDateLivraison());
        livraison.setNbLivree(livraisonDetails.getNbLivree());
        livraison.setNoBL(livraisonDetails.getNoBL());

        livraison = livraisonRepository.save(livraison);

        // Associer la livraison à la commande
        commande.setLivraison(livraison);

        // Sauvegarder la commande mise à jour
        return commandeRepository.save(commande);
    }

}
