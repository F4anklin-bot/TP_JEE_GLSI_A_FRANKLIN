package ega.banque.gestion.service.impl;

import ega.banque.gestion.entity.Client;
import ega.banque.gestion.repository.ClientRepository;
import ega.banque.gestion.service.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    //creer un client
    @Override
    public Client creerClient(Client client) {
        if (clientRepository.existsByCourriel(client.getCourriel())) {
            throw new RuntimeException("Courriel déjà utilisé " + client.getCourriel());
        }
        return clientRepository.save(client);
    }

    //Lire les infos d'un client
    @Override
    public Client getClientById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aucun client trouve avec cet ID: " +id ));
    }


    //Lire tous les clients
    @Override
    public List<Client> getAllClients(){
        return clientRepository.findAll();
    }


    //Modifier un client
    @Override
    public Client modifierClient(Long id, Client client){

        Client clientExistant = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client non trouvé"));

        clientExistant.setNom(client.getNom());
        clientExistant.setPrenom(client.getPrenom());
        clientExistant.setDateNaissance(client.getDateNaissance());
        clientExistant.setSexe(client.getSexe());
        clientExistant.setAdresse(client.getAdresse());
        clientExistant.setNumTelephone(client.getNumTelephone());
        clientExistant.setCourriel(client.getCourriel());
        clientExistant.setNationalite((client.getNationalite()));

        return clientRepository.save(clientExistant);
    }


    //Supprimer un client
    @Override
    public void supprimerClient(Long id){
        if (!clientRepository.existsById(id)){
            throw new RuntimeException("Client introuvable");

        }
        clientRepository.deleteById(id);
    }
}
