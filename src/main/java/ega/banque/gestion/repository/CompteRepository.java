package ega.banque.gestion.repository;

import ega.banque.gestion.entity.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompteRepository extends JpaRepository<Compte, Long> {
    Optional<Compte> findByNumCompte(String numCompte);

    boolean existsByNumCompte(String numCompte);
}
