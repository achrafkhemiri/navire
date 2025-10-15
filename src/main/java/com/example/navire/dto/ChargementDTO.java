package com.example.navire.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChargementDTO {

    private Long id;
    private Long camionId;
    private Long chauffeurId;
    private String societe;
    private Long projetId;
    
    // Données du projet
    private String nomProjet;
    private String produit;
    private String navire;
    private String port;
    
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateChargement;
}
