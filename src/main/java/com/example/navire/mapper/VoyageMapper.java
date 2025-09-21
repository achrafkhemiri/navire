package com.example.navire.mapper;

import com.example.navire.model.Voyage;
import com.example.navire.dto.VoyageDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface VoyageMapper {
    @Mapping(target = "chauffeurNom", ignore = true)
    @Mapping(target = "camionNom", ignore = true)
    @Mapping(target = "clientNum", ignore = true)
    @Mapping(target = "depotNom", ignore = true)
    VoyageDTO toDTO(Voyage voyage);

    Voyage toEntity(VoyageDTO dto);

    @AfterMapping
    default void mapCustomFields(Voyage voyage, @MappingTarget VoyageDTO dto) {
        if (voyage.getChauffeur() != null) {
            dto.setChauffeurNom(voyage.getChauffeur().getNom());
        }
        if (voyage.getCamion() != null) {
            dto.setCamionNom(voyage.getCamion().getMatricule());
        }
        if (voyage.getClient() != null) {
            dto.setClientNum(voyage.getClient().getNumero());
        }
        if (voyage.getDepot() != null) {
            dto.setDepotNom(voyage.getDepot().getNom());
        }
    }
}
