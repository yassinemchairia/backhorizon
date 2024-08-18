package com.example.horizon.Repository;
import com.example.horizon.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long>{

    List<Commande> findByClient(Client client);

   // List<Commande> findByClientId(Long clientId);
   List<Commande> findByClientCodeClient(Long clientCode);
    List<Commande> findByClientAndDateBCBetweenAndSeparationTraitementAndLivraisonStatusAndMiseStatus(
            Client client,
            Date startDate,
            Date endDate,
            SeparationTraitement separationTraitement,
            LivraisonStatus livraisonStatus,
            MiseStatus miseStatus
    );
}
