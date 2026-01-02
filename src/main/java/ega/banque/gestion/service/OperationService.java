package ega.banque.gestion.service;

import ega.banque.gestion.entity.Operation;

public interface OperationService {
    void Depot(Long compteId, Double montant);

    void Retrait(Long compteId, Double montant);

    void Transfert(Long compteSourceId, Long compteDestinationId, Double montant);
}
