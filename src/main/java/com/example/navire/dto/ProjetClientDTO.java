package com.example.navire.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProjetClientDTO {
    private Long id;
    private Long projetId;
    private Long clientId;
    private Double quantiteAutorisee;
}
