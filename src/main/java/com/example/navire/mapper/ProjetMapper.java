package com.example.navire.mapper;

import com.example.navire.model.Projet;
import com.example.navire.dto.ProjetDTO;
import org.springframework.stereotype.Component;

@Component
public class ProjetMapper {
    public ProjetDTO toDTO(Projet projet) {
        if (projet == null) return null;
        return new ProjetDTO(
            projet.getId(),
            projet.getNom(),
            projet.getNomProduit(),
            projet.getQuantiteTotale(),
            projet.getNomNavire(),
            projet.getPaysNavire()
        );
    }

    public Projet toEntity(ProjetDTO dto) {
        if (dto == null) return null;
        Projet projet = new Projet();
        projet.setId(dto.getId());
        projet.setNom(dto.getNom());
        projet.setNomProduit(dto.getNomProduit());
        projet.setQuantiteTotale(dto.getQuantiteTotale());
        projet.setNomNavire(dto.getNomNavire());
        projet.setPaysNavire(dto.getPaysNavire());
        return projet;
    }
}
