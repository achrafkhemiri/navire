package com.example.navire.mapper;

import com.example.navire.dto.DechargementDTO;
import com.example.navire.model.Dechargement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DechargementMapper {

    @Mapping(source = "chargement.id", target = "chargementId")
    @Mapping(source = "client.id", target = "clientId")
    @Mapping(source = "depot.id", target = "depotId")
    @Mapping(source = "chargement.camion.id", target = "camionId")
    @Mapping(source = "chargement.chauffeur.id", target = "chauffeurId")
    @Mapping(source = "chargement.societe", target = "societe")
    @Mapping(source = "chargement.projet.id", target = "projetId")
    @Mapping(source = "chargement.projet.nom", target = "nomProjet")
    @Mapping(source = "chargement.projet.nomProduit", target = "produit")
    @Mapping(source = "chargement.projet.nomNavire", target = "navire")
    @Mapping(source = "chargement.projet.port", target = "port")
    @Mapping(source = "chargement.dateChargement", target = "dateChargement")
    DechargementDTO toDTO(Dechargement dechargement);

    @Mapping(source = "chargementId", target = "chargement.id")
    @Mapping(source = "clientId", target = "client.id")
    @Mapping(source = "depotId", target = "depot.id")
    Dechargement toEntity(DechargementDTO dto);

    @Mapping(source = "chargementId", target = "chargement.id")
    @Mapping(source = "clientId", target = "client.id")
    @Mapping(source = "depotId", target = "depot.id")
    void updateEntityFromDTO(DechargementDTO dto, @MappingTarget Dechargement dechargement);
}
