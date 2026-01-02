package ega.banque.gestion.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "operations")
public class Operation {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "typeTransaction", nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeOperation typeTransaction;

    @Column(name = "montant", nullable = false)
    private Double montant;

    @Column(name = "dateOperation", nullable = false)
    private LocalDateTime dateOperation;

    @ManyToOne
    private Compte compteSource;

    @ManyToOne
    private Compte compteDestination;



    @PrePersist
    public void initDate(){
        this.dateOperation = LocalDateTime.now();
    }

}
