package com.example.navire.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CamionDTO {
    private Long id;
    private String matricule;
    private String societe;
}
