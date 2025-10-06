package com.example.navire.dto;
import java.util.Set;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VoyageDTO {
    private Long id;
    private String numBonLivraison;
    private String numTicket;
    private Double reste;
    private LocalDate date;
    private Double poidsClient;
    private Double poidsDepot;
    private Long chauffeurId;
    private String chauffeurNom;
    private Long camionId;
    private String camionNom;
    private Long clientId;
    private String clientNum;
    private Long depotId;
    private String depotNom;
    private Long projetId;
    private Long userId; // Ajout du champ userId
    private Double quantite; // Quantité du voyage
    private Long projetClientId; // Lien vers ProjetClient pour les voyages clients
}
