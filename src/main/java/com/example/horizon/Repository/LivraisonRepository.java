package com.example.horizon.Repository;

import com.example.horizon.Entity.Client;
import com.example.horizon.Entity.Livraison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivraisonRepository extends JpaRepository<Livraison, Long> {
}
