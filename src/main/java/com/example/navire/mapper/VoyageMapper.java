package com.example.navire.mapper;
import java.util.Set;
import java.util.HashSet;

import com.example.navire.model.*;
import com.example.navire.dto.VoyageDTO;
import org.springframework.stereotype.Component;

@Component
public class VoyageMapper {
    public VoyageDTO toDTO(Voyage voyage) {
        if (voyage == null) return null;
        VoyageDTO dto = new VoyageDTO();
        dto.setId(voyage.getId());
        dto.setNumBonLivraison(voyage.getNumBonLivraison());
        dto.setNumTicket(voyage.getNumTicket());
        dto.setReste(voyage.getReste());
        dto.setDate(voyage.getDate());
        dto.setPoidsClient(voyage.getPoidsClient());
        dto.setPoidsDepot(voyage.getPoidsDepot());
        if (voyage.getChauffeurs() != null) {
            Set<Long> chauffeurIds = new HashSet<>();
            voyage.getChauffeurs().forEach(ch -> chauffeurIds.add(ch.getId()));
            dto.setChauffeurIds(chauffeurIds);
        }
        if (voyage.getCamions() != null) {
            Set<Long> camionIds = new HashSet<>();
            voyage.getCamions().forEach(c -> camionIds.add(c.getId()));
            dto.setCamionIds(camionIds);
        }
        dto.setClientId(voyage.getClient() != null ? voyage.getClient().getId() : null);
        dto.setDepotId(voyage.getDepot() != null ? voyage.getDepot().getId() : null);
        dto.setProjetId(voyage.getProjet() != null ? voyage.getProjet().getId() : null);
        return dto;
    }
}
