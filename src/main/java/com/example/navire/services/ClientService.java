package com.example.navire.services;

import com.example.navire.dto.ClientDTO;
import com.example.navire.exception.ClientNotFoundException;
import com.example.navire.mapper.ClientMapper;
import com.example.navire.model.Client;
import com.example.navire.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ClientMapper clientMapper;

    public List<ClientDTO> getAllClients() {
        return clientRepository.findAll().stream()
                .map(clientMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ClientDTO getClientById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));
        return clientMapper.toDTO(client);
    }

    @Transactional
    public ClientDTO createClient(ClientDTO dto) {
        if (clientRepository.existsByNumero(dto.getNumero())) {
            throw new IllegalArgumentException("Numero already exists");
        }
        Client client = clientMapper.toEntity(dto);
        return clientMapper.toDTO(clientRepository.save(client));
    }

    @Transactional
    public ClientDTO updateClient(Long id, ClientDTO dto) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));
        client.setNumero(dto.getNumero());
        client.setNom(dto.getNom());
        client.setQuantiteAutorisee(dto.getQuantiteAutorisee());
        return clientMapper.toDTO(clientRepository.save(client));
    }

    @Transactional
    public void deleteClient(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new ClientNotFoundException(id);
        }
        clientRepository.deleteById(id);
    }
}
