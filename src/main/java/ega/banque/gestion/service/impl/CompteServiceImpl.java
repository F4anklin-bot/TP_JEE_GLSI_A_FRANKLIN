package ega.banque.gestion.service.impl;

import ega.banque.gestion.entity.Client;
import ega.banque.gestion.entity.Compte;
import ega.banque.gestion.repository.ClientRepository;
import ega.banque.gestion.repository.CompteRepository;
import ega.banque.gestion.service.CompteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CompteServiceImpl implements CompteService {

    private final CompteRepository compteRepository;
    private final ClientRepository clientRepository;

    public CompteServiceImpl(CompteRepository compteRepository, ClientRepository clientRepository) {
        this.compteRepository = compteRepository;
        this.clientRepository = clientRepository;
    }


    //Creer un client
    @Override
    public Compte creerCompte(Compte compte) {

        // Vérifier si le client propriétaire existe
        if (compte.getProprietaire() == null || compte.getProprietaire().getId() == null) {
            throw new RuntimeException("Le client propriétaire est obligatoire");
        }

        Client client = clientRepository.findById(compte.getProprietaire().getId())
                .orElseThrow(() -> new RuntimeException("Client propriétaire introuvable"));

        // Associer le client récupéré au compte (sécurise l'entité)
        compte.setProprietaire(client);

        //Verifier si le compte n'existe deja pas
        if (compteRepository.existsByNumCompte(compte.getNumCompte())) {
            throw new RuntimeException("Numero de compte existant");
        }


        return compteRepository.save(compte);
    }

    //Lire un compte par id
    @Override
    public Compte getCompteById(Long id) {
        return compteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compte non trouvé"));
    }

    //Acceder à tous les comptes
    @Override
    public List<Compte> getAllComptes() {
        return compteRepository.findAll();
    }

    //Supprimer un compte
    @Override
    public void supprimerCompte(Long id) {
        if (!compteRepository.existsById(id)){
            throw new RuntimeException("Compte introuvable");
        }
        compteRepository.deleteById(id);
    }

}
