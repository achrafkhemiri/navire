package com.example.navire.mapper;

import com.example.navire.model.Client;
import com.example.navire.dto.ClientDTO;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {
    public ClientDTO toDTO(Client client) {
        if (client == null) return null;
        return new ClientDTO(
            client.getId(),
            client.getNumero(),
            client.getNom(),
            client.getQuantiteAutorisee()
        );
    }

    public Client toEntity(ClientDTO dto) {
        if (dto == null) return null;
        Client client = new Client();
        client.setId(dto.getId());
        client.setNumero(dto.getNumero());
        client.setNom(dto.getNom());
        client.setQuantiteAutorisee(dto.getQuantiteAutorisee());
        return client;
    }
}
