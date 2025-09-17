package com.example.navire.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "projets")
public class Projet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    @Column(name = "nom_produit")
    private String nomProduit;

    @Column(name = "quantite_totale")
    private Double quantiteTotale;

    @Column(name = "nom_navire")
    private String nomNavire;

    @Column(name = "pays_navire")
    private String paysNavire;

    // Relation : un projet peut avoir plusieurs voyages
    @OneToMany(mappedBy = "projet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Voyage> voyages;
}
