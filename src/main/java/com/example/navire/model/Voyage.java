package com.example.navire.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "voyages")
public class Voyage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "num_bon_livraison", nullable = false, unique = true)
    private String numBonLivraison;

    @Column(name = "num_ticket", nullable = false, unique = true)
    private String numTicket;

    @Column(nullable = false)
    private Double reste;

    @Column(nullable = false)
    private LocalDate date;

    @Column(name = "poids_client")
    private Double poidsClient;

    @Column(name = "poids_depot")
    private Double poidsDepot;

    // Relations
    @Column(name = "chauffeur_matricule", nullable = false)
    private String chauffeurMatricule;

    @ManyToOne
    @JoinColumn(name = "camion_id", nullable = false)
    private Camion camion;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "depot_id")
    private Depot depot;

    @ManyToOne
    @JoinColumn(name = "projet_id")
    private Projet projet;
}
