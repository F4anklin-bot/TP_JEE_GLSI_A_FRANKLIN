package ega.banque.gestion.Controller;

import ega.banque.gestion.entity.Client;
import ega.banque.gestion.service.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@CrossOrigin("*")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    //Creer un utilisateur
    @PostMapping
    public Client creerClient(@RequestBody Client client){
        return clientService.creerClient(client);
    }

    //Lire un client par id
    @GetMapping("/{id}")
    public Client getClient(@PathVariable Long id) {
        return clientService.getClientById(id);
    }

    //Lire tous les clients
    @GetMapping
    public List<Client> getAllClients(){
        return clientService.getAllClients();
    }

    //Modifier un client
    @PutMapping("/{id}")
    public Client modifierClient(@PathVariable Long id, @RequestBody Client client){
        return clientService.modifierClient(id, client);
    }

    //Supprimer un client
    @DeleteMapping("/{id}")
    public void supprimerClient(@PathVariable Long id){
        clientService.supprimerClient(id);
    }
}
