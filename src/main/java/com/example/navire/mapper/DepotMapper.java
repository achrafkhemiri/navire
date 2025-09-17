package com.example.navire.mapper;

import com.example.navire.model.Depot;
import com.example.navire.dto.DepotDTO;
import org.springframework.stereotype.Component;

@Component
public class DepotMapper {
    public DepotDTO toDTO(Depot depot) {
        if (depot == null) return null;
        return new DepotDTO(
            depot.getId(),
            depot.getNom()
        );
    }

    public Depot toEntity(DepotDTO dto) {
        if (dto == null) return null;
        Depot depot = new Depot();
        depot.setId(dto.getId());
        depot.setNom(dto.getNom());
        return depot;
    }
}
