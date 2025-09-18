package com.example.navire.dto;
import java.util.Set;

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

        private Set<Long> voyageIds;
}
