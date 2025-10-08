package com.example.navire.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DepotDTO {
    private Long id;
    private String nom;
    private Long projetId; // ID du projet associé (pour filtrage)
}
