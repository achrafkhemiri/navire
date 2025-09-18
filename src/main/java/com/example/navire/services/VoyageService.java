package com.example.navire.services;
import java.util.Set;
import java.util.HashSet;

import com.example.navire.dto.VoyageDTO;
import com.example.navire.exception.VoyageNotFoundException;
import com.example.navire.mapper.VoyageMapper;
import com.example.navire.model.*;
import com.example.navire.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VoyageService {
    @Autowired
    private VoyageRepository voyageRepository;
    @Autowired
    private VoyageMapper voyageMapper;
    @Autowired
    private ChauffeurRepository chauffeurRepository;
    @Autowired
    private CamionRepository camionRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private DepotRepository depotRepository;
    @Autowired
    private ProjetRepository projetRepository;

    public List<VoyageDTO> getAllVoyages() {
        return voyageRepository.findAll().stream()
                .map(voyageMapper::toDTO)
                .collect(Collectors.toList());
    }

    public VoyageDTO getVoyageById(Long id) {
        Voyage voyage = voyageRepository.findById(id)
                .orElseThrow(() -> new VoyageNotFoundException(id));
        return voyageMapper.toDTO(voyage);
    }

    @Transactional
    public VoyageDTO createVoyage(VoyageDTO dto) {
        if (voyageRepository.existsByNumBonLivraison(dto.getNumBonLivraison())) {
            throw new IllegalArgumentException("NumBonLivraison already exists");
        }
        if (voyageRepository.existsByNumTicket(dto.getNumTicket())) {
            throw new IllegalArgumentException("NumTicket already exists");
        }
        Voyage voyage = new Voyage();
        voyage.setNumBonLivraison(dto.getNumBonLivraison());
        voyage.setNumTicket(dto.getNumTicket());
        voyage.setReste(dto.getReste());
        voyage.setDate(dto.getDate());
        voyage.setPoidsClient(dto.getPoidsClient());
        voyage.setPoidsDepot(dto.getPoidsDepot());
        // Many-to-many chauffeurs
        if (dto.getChauffeurIds() != null) {
            Set<Chauffeur> chauffeurs = dto.getChauffeurIds().stream()
                .map(id -> chauffeurRepository.findById(id).orElse(null))
                .filter(ch -> ch != null)
                .collect(Collectors.toSet());
            voyage.setChauffeurs(chauffeurs);
        }
        // Many-to-many camions
        if (dto.getCamionIds() != null) {
            Set<Camion> camions = dto.getCamionIds().stream()
                .map(id -> camionRepository.findById(id).orElse(null))
                .filter(c -> c != null)
                .collect(Collectors.toSet());
            voyage.setCamions(camions);
        }
        voyage.setClient(dto.getClientId() != null ? clientRepository.findById(dto.getClientId()).orElse(null) : null);
        voyage.setDepot(dto.getDepotId() != null ? depotRepository.findById(dto.getDepotId()).orElse(null) : null);
        voyage.setProjet(dto.getProjetId() != null ? projetRepository.findById(dto.getProjetId()).orElse(null) : null);
        return voyageMapper.toDTO(voyageRepository.save(voyage));
    }

    @Transactional
    public VoyageDTO updateVoyage(Long id, VoyageDTO dto) {
        Voyage voyage = voyageRepository.findById(id)
                .orElseThrow(() -> new VoyageNotFoundException(id));
        voyage.setNumBonLivraison(dto.getNumBonLivraison());
        voyage.setNumTicket(dto.getNumTicket());
        voyage.setReste(dto.getReste());
        voyage.setDate(dto.getDate());
        voyage.setPoidsClient(dto.getPoidsClient());
        voyage.setPoidsDepot(dto.getPoidsDepot());
        // Many-to-many chauffeurs
        if (dto.getChauffeurIds() != null) {
            Set<Chauffeur> chauffeurs = dto.getChauffeurIds().stream()
                .map(chauffeurId -> chauffeurRepository.findById(chauffeurId).orElse(null))
                .filter(ch -> ch != null)
                .collect(Collectors.toSet());
            voyage.setChauffeurs(chauffeurs);
        }
        // Many-to-many camions
        if (dto.getCamionIds() != null) {
            Set<Camion> camions = dto.getCamionIds().stream()
                .map(camionId -> camionRepository.findById(camionId).orElse(null))
                .filter(c -> c != null)
                .collect(Collectors.toSet());
            voyage.setCamions(camions);
        }
        voyage.setClient(dto.getClientId() != null ? clientRepository.findById(dto.getClientId()).orElse(null) : null);
        voyage.setDepot(dto.getDepotId() != null ? depotRepository.findById(dto.getDepotId()).orElse(null) : null);
        voyage.setProjet(dto.getProjetId() != null ? projetRepository.findById(dto.getProjetId()).orElse(null) : null);
        return voyageMapper.toDTO(voyageRepository.save(voyage));
    }

    @Transactional
    public void deleteVoyage(Long id) {
        if (!voyageRepository.existsById(id)) {
            throw new VoyageNotFoundException(id);
        }
        voyageRepository.deleteById(id);
    }
}
