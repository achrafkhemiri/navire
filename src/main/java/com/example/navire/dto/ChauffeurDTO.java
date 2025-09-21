package com.example.navire.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChauffeurDTO {
    private Long id;
    private String nom;
    private String numCin;
    private java.util.Set<String> numBonLivraisonVoyages;
}
