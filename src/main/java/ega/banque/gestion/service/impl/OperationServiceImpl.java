package ega.banque.gestion.service.impl;

import ega.banque.gestion.entity.Compte;
import ega.banque.gestion.entity.Operation;
import ega.banque.gestion.entity.TypeOperation;
import ega.banque.gestion.repository.CompteRepository;
import ega.banque.gestion.repository.OperationRepository;
import ega.banque.gestion.service.OperationService;
import org.springframework.stereotype.Service;

@Service
public class OperationServiceImpl implements OperationService {
    private final CompteRepository compteRepository;
    private final OperationRepository operationRepository;

    public OperationServiceImpl(CompteRepository compteRepository, OperationRepository operationRepository) {
        this.compteRepository = compteRepository;
        this.operationRepository = operationRepository;
    }


    // Effectuer un depot
    @Override
    public void Depot(Long compteId, Double montant){
        Compte compte = compteRepository.findById(compteId)
                .orElseThrow(() -> new RuntimeException("Compte non trouvé"));

        compte.setSolde(compte.getSolde() + montant);

        Operation operation = new Operation();

        operation.setTypeTransaction(TypeOperation.DEPOT);
        operation.setMontant(montant);
        operation.setCompteDestination(compte);

        operationRepository.save(operation);

    }


    // Effectuer un retrait

    @Override
    public void Retrait(Long compteId, Double montant){
        Compte compte = compteRepository.findById(compteId)
                .orElseThrow(() -> new RuntimeException("Compte non trouvé"));

        if (compte.getSolde() < montant) {
            throw new RuntimeException("Solde insuffisant");
        }
        compte.setSolde(compte.getSolde() - montant);

        Operation operation = new Operation();

        operation.setTypeTransaction(TypeOperation.RETRAIT);
        operation.setMontant(montant);
        operation.setCompteSource(compte);

        operationRepository.save(operation);



    }

    public void Transfert(Long compteSourceId, Long compteDestinationId, Double montant){

        Compte compteSource = compteRepository.findById(compteSourceId)
                .orElseThrow(() -> new RuntimeException("Compte non trouvé"));

        Compte compteDestination = compteRepository.findById(compteDestinationId)
                .orElseThrow(() -> new RuntimeException("Compte non trouvé"));

        if (compteSource.getSolde() < montant) {
            throw new RuntimeException("Montant insuffisant");
        }


        compteSource.setSolde(compteSource.getSolde() - montant);

        compteDestination.setSolde(compteDestination.getSolde() + montant);

        Operation operation = new Operation();

        operation.setTypeTransaction(TypeOperation.TRANSFERT);
        operation.setMontant(montant);
        operation.setCompteSource(compteSource);
        operation.setCompteDestination(compteDestination);

        operationRepository.save(operation);
    }




}
