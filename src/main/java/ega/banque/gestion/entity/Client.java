package ega.banque.gestion.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "id")
    private Long id;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "prenom", nullable = false)
    private String prenom;

    @Column(name = "dateNaissance")
    private LocalDate dateNaissance;

    @Column(name = "sexe", nullable = false)
    @Enumerated(EnumType.STRING)
    private Sexe sexe;

    @Column(name = "adresse", nullable = false)
    private String adresse;

    @Column(name = "NumTelephone", nullable = false)
    private String numTelephone;

    @Column(name = "courriel", unique = true, nullable = false)
    private String courriel;

    @Column(name = "nationalite", nullable = false)
    private String nationalite;


    @OneToMany(mappedBy = "proprietaire", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Compte> comptes;

}
