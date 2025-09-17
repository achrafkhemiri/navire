package com.example.navire.mapper;

import com.example.navire.model.*;
import com.example.navire.dto.VoyageDTO;
import org.springframework.stereotype.Component;

@Component
public class VoyageMapper {
    public VoyageDTO toDTO(Voyage voyage) {
        if (voyage == null) return null;
        return new VoyageDTO(
            voyage.getId(),
            voyage.getNumBonLivraison(),
            voyage.getNumTicket(),
            voyage.getReste(),
            voyage.getDate(),
            voyage.getPoidsClient(),
            voyage.getPoidsDepot(),
            voyage.getChauffeurMatricule(),
            voyage.getCamion() != null ? voyage.getCamion().getId() : null,
            voyage.getClient() != null ? voyage.getClient().getId() : null,
            voyage.getDepot() != null ? voyage.getDepot().getId() : null,
            voyage.getProjet() != null ? voyage.getProjet().getId() : null
        );
    }
}
