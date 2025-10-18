package com.example.navire.mapper;

import com.example.navire.model.Societe;
import com.example.navire.dto.SocieteDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SocieteMapper {
    SocieteDTO toDTO(Societe societe);
    Societe toEntity(SocieteDTO dto);
}
