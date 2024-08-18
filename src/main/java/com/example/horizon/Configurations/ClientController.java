package com.example.horizon.Configurations;
import com.example.horizon.DTO.CommandeDTO;
import com.example.horizon.DTO.MatiereDTO;
import com.example.horizon.Entity.*;
import com.example.horizon.Repository.ClientRepository;
import com.example.horizon.Repository.CommandeRepository;
import com.example.horizon.Repository.MatiereRepository;
import com.example.horizon.Service.ClientService;
import com.example.horizon.Service.CommandeService;
import com.example.horizon.Service.MatiereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    MatiereRepository matiereRepository;
    @Autowired
    private ClientService clientService;
    @Autowired
    private CommandeRepository commandeRepository;;
    // Endpoint pour enregistrer un nouveau client
    @PostMapping("/register")
    public ResponseEntity<?> registerClient(@RequestBody Client client) {
        Client savedClient = clientService.registerClient(client);
        return ResponseEntity.ok(savedClient);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String login, @RequestParam String motDePasse) {
        Client client = clientService.authenticate(login, motDePasse);

        if (client != null) {
            List<Commande> commandes = commandeRepository.findByClient(client);

            // Créer une réponse contenant les informations du client et ses commandes
            Map<String, Object> response = new HashMap<>();
            response.put("client", client);
            response.put("commandes", commandes);

            return ResponseEntity.ok(response); // Authentification réussie avec les commandes incluses
        } else {
            return ResponseEntity.status(401).body("Login ou mot de passe incorrect."); // Authentification échouée
        }
    }

    @Autowired
    private MatiereService matiereService;

    @PostMapping("/matiere")
    public ResponseEntity<Matiere> createMatiere(@RequestBody MatiereDTO matiereDTO) {
        Matiere createdMatiere = matiereService.addMatiere(matiereDTO);
        return ResponseEntity.ok(createdMatiere);
    }
    @Autowired
    private CommandeService commandeService;

    @PostMapping("/ajouter")
    public ResponseEntity<Commande> ajouterCommande(@RequestBody CommandeDTO commandeDTO) {
        Client client = clientRepository.findByNomClient(commandeDTO.getNomClient())
                .orElseThrow(() -> new RuntimeException("Client non trouvé"));

        Commande nouvelleCommande = new Commande();
        nouvelleCommande.setClient(client);
        nouvelleCommande.setNoBC(commandeDTO.getNoBC());  // Saisir noBC
        nouvelleCommande.setNoMise(commandeDTO.getNoMise());  // Saisir noMise
        nouvelleCommande.setDateBC(new Date());
        nouvelleCommande.setDateLancement(new Date());
        nouvelleCommande.setMiseStatus(commandeDTO.getMiseStatus());
        nouvelleCommande.setSeparationTraitement(commandeDTO.getSeparationTraitement());
        if (commandeDTO.getLivraisonStatus() == null) {
            nouvelleCommande.setLivraisonStatus(LivraisonStatus.NON_LIVREES);
        } else {
            nouvelleCommande.setLivraisonStatus(commandeDTO.getLivraisonStatus());
        }
        // Ajout des matières
        List<Matiere> matieres = matiereRepository.findAllById(commandeDTO.getMatiereIds());
        nouvelleCommande.setMatieres(matieres);

        // Enregistrer la commande
        Commande savedCommande = commandeService.save(nouvelleCommande);

        return ResponseEntity.ok(savedCommande);
    }
    @PostMapping("/{idCom}/livrer")
    public ResponseEntity<Commande> livrerCommande(
            @PathVariable Long idCom,
            @RequestBody Livraison livraisonDetails) {
        Commande commandeLivree = commandeService.livrerCommande(idCom, livraisonDetails);
        return ResponseEntity.ok(commandeLivree);
    }
    @GetMapping("/with-livraisons")
    public List<Commande> getCommandesWithLivraisons() {
        return commandeRepository.findAll();
    }
    @PostMapping("/filterCommandes")
    public ResponseEntity<?> filterCommandes(
            @RequestParam String login,
            @RequestParam String motDePasse,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            @RequestParam SeparationTraitement separationTraitement,
            @RequestParam LivraisonStatus livraisonStatus,
            @RequestParam MiseStatus miseStatus) {

        Client client = clientService.authenticate(login, motDePasse);

        if (client != null) {
            List<Commande> commandes = clientService.getFilteredCommandes(client, startDate, endDate, separationTraitement, livraisonStatus, miseStatus);

            Map<String, Object> response = new HashMap<>();
            response.put("client", client);
            response.put("commandes", commandes);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body("Login ou mot de passe incorrect.");
        }
    }

}
