package com.example.navire.mapper;

import com.example.navire.model.Client;
import com.example.navire.dto.ClientDTO;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {
    // Map Client entity to ClientDTO, null-safe
    public ClientDTO toDTO(Client client) {
        if (client == null) return null;
        ClientDTO dto = new ClientDTO();
        dto.setId(client.getId());
        dto.setNumero(client.getNumero());
        dto.setNom(client.getNom());
        // Map quantites autorisees par projet
        java.util.Map<Long, Double> quantitesMap = new java.util.HashMap<>();
        if (client.getProjetClients() != null) {
            client.getProjetClients().forEach(pc -> {
                if (pc.getProjet() != null && pc.getQuantiteAutorisee() != null) {
                    quantitesMap.put(pc.getProjet().getId(), pc.getQuantiteAutorisee());
                }
            });
        }
        dto.setQuantitesAutoriseesParProjet(quantitesMap);
        return dto;
    }

    // Map ClientDTO to Client entity, null-safe
    public Client toEntity(ClientDTO dto) {
        if (dto == null) return null;
        Client client = new Client();
        client.setId(dto.getId());
        client.setNumero(dto.getNumero());
        client.setNom(dto.getNom());
        // Mapping projetClients from DTO is not handled here (needs service logic)
        return client;
    }
}
