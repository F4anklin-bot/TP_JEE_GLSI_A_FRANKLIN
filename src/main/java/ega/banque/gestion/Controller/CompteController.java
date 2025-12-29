package ega.banque.gestion.Controller;

import ega.banque.gestion.entity.Compte;
import ega.banque.gestion.service.CompteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comptes")
@CrossOrigin("*")
public class CompteController {
    private final CompteService compteService;

    public CompteController(CompteService compteService) {
        this.compteService = compteService;
    }

    //Creer un compte
    @PostMapping
    public Compte creerCompte(@RequestBody Compte compte) {
        return compteService.creerCompte(compte);
    }

    //Lire un compte par id
    @GetMapping("/{id}")
    public Compte getCompteById(@PathVariable Long id){
        return compteService.getCompteById(id);
    }

    //Lire tous les comptes
    @GetMapping
    public List<Compte> getAllComptes() {
        return compteService.getAllComptes();
    }

    //Supprimer un compte
    @DeleteMapping("/{id}")
    public void supprimerCompte(@PathVariable Long id) {
        compteService.supprimerCompte(id);
    }
}
