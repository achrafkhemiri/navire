package com.example.navire.model;
import java.util.Set;
import java.util.HashSet;
import java.util.Set;
import java.util.HashSet;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @NotNull(message = "Le numéro du bon de livraison est obligatoire")
    @Size(min = 2, max = 30, message = "Le numéro du bon de livraison doit comporter entre 2 et 30 caractères")
    @Column(name = "num_bon_livraison", nullable = false, unique = true)
    private String numBonLivraison;

    @NotNull(message = "Le numéro du ticket est obligatoire")
    @Size(min = 2, max = 30, message = "Le numéro du ticket doit comporter entre 2 et 30 caractères")
    @Column(name = "num_ticket", nullable = false, unique = true)
    private String numTicket;

    @NotNull(message = "Le reste est obligatoire")
    @Min(value = 0, message = "Le reste doit être positif")
    @Column(nullable = false)
    private Double reste;

    @NotNull(message = "La date est obligatoire")
    @Column(nullable = false)
    private LocalDate date;

    @Column(name = "poids_client")
    private Double poidsClient;

    @Column(name = "poids_depot")
    private Double poidsDepot;

    // Relations
    @ManyToOne
    @JoinColumn(name = "chauffeur_id")
    private Chauffeur chauffeur;

    @NotNull(message = "Le camion est obligatoire")
    @ManyToOne
    @JoinColumn(name = "camion_id")
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

    // Ajout relation ManyToOne vers User
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
