package com.example.horizon.Service;
import com.example.horizon.Entity.*;
import com.example.horizon.Repository.ClientRepository;
import com.example.horizon.Repository.CommandeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor

public class ClientService {


    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private CommandeRepository commandeRepository;


    public Client authenticate(String login, String motDePasse) {
        // Rechercher le client par son login
        Client client = clientRepository.findByLogin(login);

        // Vérifier si le client existe et si le mot de passe est correct
        if (client != null && client.getMotDePasse().equals(motDePasse)) {
            return client; // Authentification réussie
        } else {
            return null; // Authentification échouée
        }
    }
    public List<Commande> getCommandesByClient(Long codeClient) {
        return commandeRepository.findByClientCodeClient(codeClient);
    }
    public Client registerClient(Client client) {
        return clientRepository.save(client);
    }
    public List<Commande> getFilteredCommandes(Client client, Date startDate, Date endDate,
                                               SeparationTraitement separationTraitement,
                                               LivraisonStatus livraisonStatus,
                                               MiseStatus miseStatus) {
        return commandeRepository.findByClientAndDateBCBetweenAndSeparationTraitementAndLivraisonStatusAndMiseStatus(
                client,
                startDate,
                endDate,
                separationTraitement,
                livraisonStatus,
                miseStatus
        );
    }
}
