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
    private Set<Long> chauffeurIds;
        private Set<Long> camionIds;
    private Long clientId;
    private Long depotId;
    private Long projetId;
}
