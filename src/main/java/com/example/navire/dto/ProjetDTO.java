package com.example.navire.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProjetDTO {
    private Long id;
    private String nom;
    private String nomProduit;
    private Double quantiteTotale;
    private String nomNavire;
    private String paysNavire;
    private String etat;
}
