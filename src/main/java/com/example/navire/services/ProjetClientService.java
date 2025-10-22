package com.example.navire.services;

import com.example.navire.dto.ProjetClientDTO;
import com.example.navire.exception.ClientNotFoundException;
import com.example.navire.exception.ProjetNotFoundException;
import com.example.navire.exception.QuantiteDepassementException;
import com.example.navire.mapper.ProjetClientMapper;
import com.example.navire.model.ProjetClient;
import com.example.navire.model.Projet;
import com.example.navire.model.Client;
import com.example.navire.repository.ProjetClientRepository;
import com.example.navire.repository.ProjetRepository;
import com.example.navire.repository.ClientRepository;
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
    private ProjetRepository projetRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ProjetClientMapper projetClientMapper;
    @Autowired
    private QuantiteService quantiteService;

    public List<ProjetClientDTO> getAllProjetClients() {
        return projetClientRepository.findAll().stream()
                .map(projetClientMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<ProjetClientDTO> getProjetClientsByProjetId(Long projetId) {
        return projetClientRepository.findByProjetId(projetId).stream()
                .map(projetClientMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<ProjetClientDTO> getProjetClientsByClientId(Long clientId) {
        return projetClientRepository.findByClientId(clientId).stream()
                .map(projetClientMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ProjetClientDTO getProjetClientById(Long id) {
        ProjetClient projetClient = projetClientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProjetClient not found: " + id));
        return projetClientMapper.toDTO(projetClient);
    }

    @Transactional
    public ProjetClientDTO createProjetClient(ProjetClientDTO projetClientDTO) {
        // Valider la quantité avant d'ajouter
        QuantiteService.ValidationResult validation = quantiteService.validerAjoutClient(
            projetClientDTO.getProjetId(), 
            projetClientDTO.getQuantiteAutorisee()
        );
        
        if (!validation.isValide()) {
            throw new QuantiteDepassementException(validation.getMessage());
        }

        // Récupérer le projet et le client
        Projet projet = projetRepository.findById(projetClientDTO.getProjetId())
                .orElseThrow(() -> new ProjetNotFoundException(projetClientDTO.getProjetId()));
        
        Client client = clientRepository.findById(projetClientDTO.getClientId())
                .orElseThrow(() -> new ClientNotFoundException(projetClientDTO.getClientId()));
        
        // Créer le ProjetClient
        ProjetClient projetClient = new ProjetClient();
        projetClient.setProjet(projet);
        projetClient.setClient(client);
        projetClient.setQuantiteAutorisee(projetClientDTO.getQuantiteAutorisee());
        
        projetClientRepository.save(projetClient);
        return projetClientMapper.toDTO(projetClient);
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
                throw new QuantiteDepassementException(validation.getMessage());
            }
        }
        
        projetClient.setQuantiteAutorisee(quantiteAutorisee);
        projetClientRepository.save(projetClient);
        return projetClientMapper.toDTO(projetClient);
    }

    @Transactional
    public void deleteProjetClient(Long id) {
        ProjetClient projetClient = projetClientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProjetClient not found: " + id));
        projetClientRepository.delete(projetClient);
    }
}
