package com.example.navire.services;

import com.example.navire.dto.DepotDTO;
import com.example.navire.exception.DepotNotFoundException;
import com.example.navire.mapper.DepotMapper;
import com.example.navire.model.Depot;
import com.example.navire.repository.DepotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepotService {
    @Autowired
    private DepotRepository depotRepository;
    @Autowired
    private DepotMapper depotMapper;

    public List<DepotDTO> getAllDepots() {
        return depotRepository.findAll().stream()
                .map(depotMapper::toDTO)
                .collect(Collectors.toList());
    }

    public DepotDTO getDepotById(Long id) {
        Depot depot = depotRepository.findById(id)
                .orElseThrow(() -> new DepotNotFoundException(id));
        return depotMapper.toDTO(depot);
    }

    @Transactional
    public DepotDTO createDepot(DepotDTO dto) {
        if (depotRepository.existsByNom(dto.getNom())) {
            throw new IllegalArgumentException("Nom already exists");
        }
        Depot depot = depotMapper.toEntity(dto);
        return depotMapper.toDTO(depotRepository.save(depot));
    }

    @Transactional
    public DepotDTO updateDepot(Long id, DepotDTO dto) {
        Depot depot = depotRepository.findById(id)
                .orElseThrow(() -> new DepotNotFoundException(id));
        depot.setNom(dto.getNom());
        return depotMapper.toDTO(depotRepository.save(depot));
    }

    @Transactional
    public void deleteDepot(Long id) {
        if (!depotRepository.existsById(id)) {
            throw new DepotNotFoundException(id);
        }
        depotRepository.deleteById(id);
    }
}
