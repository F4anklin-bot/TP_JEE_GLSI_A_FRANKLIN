package ega.banque.gestion.service;

import ega.banque.gestion.entity.Compte;

import java.util.List;

public interface CompteService {

    Compte creerCompte(Compte compte);

    Compte getCompteById(Long id);

    List<Compte> getAllComptes();

    void supprimerCompte(Long id);

}
