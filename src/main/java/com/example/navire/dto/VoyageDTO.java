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
    private Long camionId;
    private Long clientId;
    private Long depotId;
    private Long projetId;
    private Long userId; // Ajout du champ userId
}
