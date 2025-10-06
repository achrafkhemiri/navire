package com.example.navire.mapper;

import com.example.navire.model.ProjetClient;
import com.example.navire.dto.ProjetClientDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ProjetClientMapper {
    @Mapping(source = "projet.id", target = "projetId")
    @Mapping(source = "client.id", target = "clientId")
    ProjetClientDTO toDTO(ProjetClient projetClient);
    
    ProjetClient toEntity(ProjetClientDTO dto);
}
