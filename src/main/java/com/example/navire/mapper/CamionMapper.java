package com.example.navire.mapper;

import com.example.navire.model.Camion;
import com.example.navire.dto.CamionDTO;
import org.springframework.stereotype.Component;

@Component
public class CamionMapper {
    public CamionDTO toDTO(Camion camion) {
        if (camion == null) return null;
        return new CamionDTO(
            camion.getId(),
            camion.getMatricule(),
            camion.getSociete()
        );
    }

    public Camion toEntity(CamionDTO dto) {
        if (dto == null) return null;
        Camion camion = new Camion();
        camion.setId(dto.getId());
        camion.setMatricule(dto.getMatricule());
        camion.setSociete(dto.getSociete());
        return camion;
    }
}
