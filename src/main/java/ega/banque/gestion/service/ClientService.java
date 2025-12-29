package ega.banque.gestion.service;

import ega.banque.gestion.entity.Client;
import org.springframework.stereotype.Service;

import java.util.List;



public interface ClientService {
    Client creerClient(Client client);

    Client modifierClient(Long id, Client client);

    Client getClientById(Long id);

    List<Client> getAllClients();

    void supprimerClient(Long id);
}
