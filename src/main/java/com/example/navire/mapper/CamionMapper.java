package com.example.navire.mapper;
import java.util.Set;
import java.util.HashSet;

import com.example.navire.model.Camion;
import com.example.navire.dto.CamionDTO;
import org.springframework.stereotype.Component;

@Component
public class CamionMapper {
    public CamionDTO toDTO(Camion camion) {
        if (camion == null) return null;
        CamionDTO dto = new CamionDTO();
        dto.setId(camion.getId());
        dto.setMatricule(camion.getMatricule());
        dto.setSociete(camion.getSociete());
        dto.setNumBonLivraison(camion.getNumBonLivraison());
        return dto;
    }

    public Camion toEntity(CamionDTO dto) {
        if (dto == null) return null;
        Camion camion = new Camion();
        camion.setId(dto.getId());
        camion.setMatricule(dto.getMatricule());
        camion.setSociete(dto.getSociete());
    camion.setNumBonLivraison(dto.getNumBonLivraison());
    return camion;
    }
}
