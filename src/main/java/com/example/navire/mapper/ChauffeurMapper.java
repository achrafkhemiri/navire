package com.example.navire.mapper;

import com.example.navire.model.Chauffeur;
import com.example.navire.dto.ChauffeurDTO;
import org.springframework.stereotype.Component;

@Component
public class ChauffeurMapper {
    public ChauffeurDTO toDTO(Chauffeur chauffeur) {
        if (chauffeur == null) return null;
        return new ChauffeurDTO(
            chauffeur.getId(),
            chauffeur.getNom(),
            chauffeur.getNumCin()
        );
    }

    public Chauffeur toEntity(ChauffeurDTO dto) {
        if (dto == null) return null;
        Chauffeur chauffeur = new Chauffeur();
        chauffeur.setId(dto.getId());
        chauffeur.setNom(dto.getNom());
        chauffeur.setNumCin(dto.getNumCin());
        return chauffeur;
    }
}
