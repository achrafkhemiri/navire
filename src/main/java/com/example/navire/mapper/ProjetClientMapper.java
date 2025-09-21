package com.example.navire.mapper;

import com.example.navire.model.ProjetClient;
import com.example.navire.dto.ProjetClientDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ProjetClientMapper {
    ProjetClientDTO toDTO(ProjetClient projetClient);
    ProjetClient toEntity(ProjetClientDTO dto);
}
