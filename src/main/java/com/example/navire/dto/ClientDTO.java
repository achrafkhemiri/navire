package com.example.navire.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ClientDTO {
    private Long id;
    private String numero;
    private String nom;
    private Double quantiteAutorisee;
}
