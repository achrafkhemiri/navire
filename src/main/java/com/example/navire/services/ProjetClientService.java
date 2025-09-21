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

@Service
public class ProjetClientService {
    @Autowired
    private ProjetClientRepository projetClientRepository;
    @Autowired
    private ProjetClientMapper projetClientMapper;

    @Transactional
    public ProjetClientDTO updateQuantiteAutorisee(Long projetClientId, Double quantiteAutorisee) {
        ProjetClient projetClient = projetClientRepository.findById(projetClientId)
                .orElseThrow(() -> new RuntimeException("ProjetClient not found: " + projetClientId));
        projetClient.setQuantiteAutorisee(quantiteAutorisee);
        projetClientRepository.save(projetClient);
        return projetClientMapper.toDTO(projetClient);
    }
}
