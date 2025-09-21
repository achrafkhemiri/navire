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
    public boolean projetExists(Long projetId) {
        return projetRepository.existsById(projetId);
    }
    public List<VoyageDTO> getVoyagesByProjetId(Long projetId) {
        return voyageRepository.findByProjetId(projetId)
            .stream()
            .map(voyageMapper::toDTO)
            .collect(java.util.stream.Collectors.toList());
    }
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

    @Autowired
    private UserRepository userRepository;

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
        // Ajout du chauffeur unique
        if (dto.getChauffeurId() != null) {
            Chauffeur chauffeur = chauffeurRepository.findById(dto.getChauffeurId()).orElse(null);
            voyage.setChauffeur(chauffeur);
        } else {
            voyage.setChauffeur(null);
        }
        // Ajout du camion unique
        if (dto.getCamionId() != null) {
            Camion camion = camionRepository.findById(dto.getCamionId()).orElse(null);
            voyage.setCamion(camion);
        } else {
            voyage.setCamion(null);
        }
        voyage.setClient(dto.getClientId() != null ? clientRepository.findById(dto.getClientId()).orElse(null) : null);
        voyage.setDepot(dto.getDepotId() != null ? depotRepository.findById(dto.getDepotId()).orElse(null) : null);
        voyage.setProjet(dto.getProjetId() != null ? projetRepository.findById(dto.getProjetId()).orElse(null) : null);
        // Ajout de l'utilisateur unique
        if (dto.getUserId() != null) {
            User user = userRepository.findById(dto.getUserId()).orElse(null);
            voyage.setUser(user);
        } else {
            voyage.setUser(null);
        }
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
        // Ajout du chauffeur unique
        if (dto.getChauffeurId() != null) {
            Chauffeur chauffeur = chauffeurRepository.findById(dto.getChauffeurId()).orElse(null);
            voyage.setChauffeur(chauffeur);
        } else {
            voyage.setChauffeur(null);
        }
        // Ajout du camion unique
        if (dto.getCamionId() != null) {
            Camion camion = camionRepository.findById(dto.getCamionId()).orElse(null);
            voyage.setCamion(camion);
        } else {
            voyage.setCamion(null);
        }
        voyage.setClient(dto.getClientId() != null ? clientRepository.findById(dto.getClientId()).orElse(null) : null);
        voyage.setDepot(dto.getDepotId() != null ? depotRepository.findById(dto.getDepotId()).orElse(null) : null);
        voyage.setProjet(dto.getProjetId() != null ? projetRepository.findById(dto.getProjetId()).orElse(null) : null);
        // Ajout de l'utilisateur unique
        if (dto.getUserId() != null) {
            User user = userRepository.findById(dto.getUserId()).orElse(null);
            voyage.setUser(user);
        } else {
            voyage.setUser(null);
        }
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
