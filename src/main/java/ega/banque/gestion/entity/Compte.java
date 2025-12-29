package ega.banque.gestion.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.iban4j.Iban;
import org.iban4j.IbanFormatException;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comptes")
public class Compte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "numCompte", nullable = false, unique = true)
    private String numCompte;

    @Enumerated(EnumType.STRING)
    @Column(name = "typeCompte", nullable = false)
    private TypeCompte typeCompte;

    @Column(name = "dateCreation")
    private LocalDate dateCreation;

    @Column(name = "solde")
    private Double solde;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client proprietaire;

    public void genererNumeroCompte() {
        try {
            Iban iban = Iban.random(); // Génère un IBAN aléatoire
            this.numCompte = iban.toString();
        } catch (IbanFormatException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour initialiser un compte
    @PrePersist
    public void init() {
        this.dateCreation = LocalDate.now();
        this.solde = 0.0;
        if(this.numCompte == null || this.numCompte.isEmpty()) {
            genererNumeroCompte();
        }
    }
}
