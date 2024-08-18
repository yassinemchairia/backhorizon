package com.example.horizon.Repository;

import com.example.horizon.Entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByLogin(String login);
    Optional<Client> findByNomClient(String nomClient);
}
