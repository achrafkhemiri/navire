package com.example.navire.mapper;

import com.example.navire.model.Depot;
import com.example.navire.dto.DepotDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepotMapper {
    DepotDTO toDTO(Depot depot);
    Depot toEntity(DepotDTO dto);
}
