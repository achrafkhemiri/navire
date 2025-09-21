package com.example.navire.mapper;

import com.example.navire.model.Projet;
import com.example.navire.dto.ProjetDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjetMapper {
    ProjetDTO toDTO(Projet projet);
    Projet toEntity(ProjetDTO dto);
}
