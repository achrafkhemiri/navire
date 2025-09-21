package com.example.navire.mapper;

import com.example.navire.model.Chauffeur;
import com.example.navire.dto.ChauffeurDTO;
import org.springframework.stereotype.Component;

@Component
public class ChauffeurMapper {
    public ChauffeurDTO toDTO(Chauffeur chauffeur) {
        if (chauffeur == null) return null;
        ChauffeurDTO dto = new ChauffeurDTO();
        dto.setId(chauffeur.getId());
        dto.setNom(chauffeur.getNom());
        dto.setNumCin(chauffeur.getNumCin());
        if (chauffeur.getVoyages() != null) {
            java.util.Set<String> numBonLivraisonVoyages = new java.util.HashSet<>();
            chauffeur.getVoyages().forEach(v -> {
                if (v.getNumBonLivraison() != null) {
                    numBonLivraisonVoyages.add(v.getNumBonLivraison());
                }
            });
            dto.setNumBonLivraisonVoyages(numBonLivraisonVoyages);
        }
        return dto;
    }

    public Chauffeur toEntity(ChauffeurDTO dto) {
        if (dto == null) return null;
        Chauffeur chauffeur = new Chauffeur();
        chauffeur.setId(dto.getId());
        chauffeur.setNom(dto.getNom());
        chauffeur.setNumCin(dto.getNumCin());
        // voyages are not mapped from DTO to entity here
        return chauffeur;
    }
}
