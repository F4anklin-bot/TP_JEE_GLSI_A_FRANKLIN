package ega.banque.gestion.repository;

import ega.banque.gestion.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByCourriel(String courriel);

    boolean existsByCourriel(String courriel);
    @Override
    Optional<Client> findById(Long id);

    Optional<Client> getClientById(Long id);
}
