package com.example.navire.services;

import com.example.navire.dto.ProjetDTO;
import com.example.navire.exception.ProjetNotFoundException;
import com.example.navire.mapper.ProjetMapper;
import com.example.navire.model.Client;
import com.example.navire.model.Projet;
import com.example.navire.model.Client;
import com.example.navire.model.Depot;
import com.example.navire.model.ProjetClient;
import com.example.navire.repository.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjetService {
    @Autowired
    private ProjetRepository projetRepository;
    @Autowired
    private ProjetMapper projetMapper;
    @Autowired
    private com.example.navire.repository.ClientRepository clientRepository;
    @Autowired
    private com.example.navire.repository.DepotRepository depotRepository;
    @Autowired
    private QuantiteService quantiteService;

    public List<ProjetDTO> getAllProjets() {
        return projetRepository.findAll().stream()
                .map(projetMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ProjetDTO getProjetById(Long id) {
        Projet projet = projetRepository.findById(id)
                .orElseThrow(() -> new ProjetNotFoundException(id));
        return projetMapper.toDTO(projet);
    }

    @Transactional
    public ProjetDTO createProjet(ProjetDTO dto) {
        Projet projet = projetMapper.toEntity(dto);
        projet.setEtat(dto.getEtat());
        projet.setDateDebut(dto.getDateDebut());
        projet.setDateFin(dto.getDateFin());
        projet.setActive(dto.getActive() != null ? dto.getActive() : true);
        return projetMapper.toDTO(projetRepository.save(projet));
    }

    @Transactional
    public ProjetDTO updateProjet(Long id, ProjetDTO dto) {
        Projet projet = projetRepository.findById(id)
                .orElseThrow(() -> new ProjetNotFoundException(id));
        projet.setNom(dto.getNom());
        projet.setNomProduit(dto.getNomProduit());
        projet.setQuantiteTotale(dto.getQuantiteTotale());
        projet.setNomNavire(dto.getNomNavire());
        projet.setPaysNavire(dto.getPaysNavire());
        projet.setEtat(dto.getEtat());
        projet.setDateDebut(dto.getDateDebut());
        projet.setDateFin(dto.getDateFin());
        projet.setActive(dto.getActive() != null ? dto.getActive() : projet.getActive());
        return projetMapper.toDTO(projetRepository.save(projet));
    }

    @Transactional
    public void deleteProjet(Long id) {
        if (!projetRepository.existsById(id)) {
            throw new ProjetNotFoundException(id);
        }
        projetRepository.deleteById(id);
    }

    @Transactional
    public void addClientToProjet(Long projetId, Long clientId) {
        addClientToProjet(projetId, clientId, 0.0);
    }

    @Transactional
    public void addClientToProjet(Long projetId, Long clientId, Double quantiteAutorisee) {
        // Valider la quantité avant d'ajouter le client
        double quantite = quantiteAutorisee != null ? quantiteAutorisee : 0.0;
        QuantiteService.ValidationResult validation = quantiteService.validerAjoutClient(projetId, quantite);
        
        if (!validation.isValide()) {
            throw new IllegalArgumentException(validation.getMessage());
        }
        
        Projet projet = projetRepository.findById(projetId).orElseThrow(() -> new ProjetNotFoundException(projetId));
        Client client = clientRepository.findById(clientId).orElseThrow();
        ProjetClient pc = new ProjetClient();
        pc.setProjet(projet);
        pc.setClient(client);
        pc.setQuantiteAutorisee(quantite);
        projet.getProjetClients().add(pc);
        projetRepository.save(projet);
    }

    @Transactional
    public void addDepotToProjet(Long projetId, Long depotId) {
        Projet projet = projetRepository.findById(projetId).orElseThrow(() -> new ProjetNotFoundException(projetId));
        Depot depot = depotRepository.findById(depotId).orElseThrow();
        projet.getDepots().add(depot);
        projetRepository.save(projet);
    }
}
