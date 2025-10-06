package com.example.navire.services;

import com.example.navire.dto.ProjetClientDTO;
import com.example.navire.exception.ClientNotFoundException;
import com.example.navire.exception.ProjetNotFoundException;
import com.example.navire.mapper.ProjetClientMapper;
import com.example.navire.model.ProjetClient;
import com.example.navire.repository.ProjetClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjetClientService {
    @Autowired
    private ProjetClientRepository projetClientRepository;
    @Autowired
    private ProjetClientMapper projetClientMapper;
    @Autowired
    private QuantiteService quantiteService;

    public List<ProjetClientDTO> getAllProjetClients() {
        return projetClientRepository.findAll().stream()
                .map(projetClientMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ProjetClientDTO updateQuantiteAutorisee(Long projetClientId, Double quantiteAutorisee) {
        ProjetClient projetClient = projetClientRepository.findById(projetClientId)
                .orElseThrow(() -> new RuntimeException("ProjetClient not found: " + projetClientId));
        
        // Calculer la différence de quantité
        double ancienneQuantite = projetClient.getQuantiteAutorisee();
        double difference = quantiteAutorisee - ancienneQuantite;
        
        // Si on augmente la quantité, valider qu'il y a assez de quantité disponible
        if (difference > 0) {
            QuantiteService.ValidationResult validation = quantiteService.validerAjoutClient(
                projetClient.getProjet().getId(), 
                difference
            );
            
            if (!validation.isValide()) {
                throw new RuntimeException(validation.getMessage());
            }
        }
        
        projetClient.setQuantiteAutorisee(quantiteAutorisee);
        projetClientRepository.save(projetClient);
        return projetClientMapper.toDTO(projetClient);
    }
    
    @Transactional
    public ProjetClientDTO ajouterClientAuProjet(Long projetId, Long clientId, Double quantiteAutorisee) {
        // Valider la quantité avant d'ajouter
        QuantiteService.ValidationResult validation = quantiteService.validerAjoutClient(
            projetId, 
            quantiteAutorisee
        );
        
        if (!validation.isValide()) {
            throw new RuntimeException(validation.getMessage());
        }
        
        // Créer le ProjetClient
        ProjetClient projetClient = new ProjetClient();
        // TODO: Set projet and client from repositories
        projetClient.setQuantiteAutorisee(quantiteAutorisee);
        
        projetClientRepository.save(projetClient);
        return projetClientMapper.toDTO(projetClient);
    }
}
