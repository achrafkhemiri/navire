package com.example.navire.services;

import com.example.navire.dto.ProjetDTO;
import com.example.navire.exception.ProjetNotFoundException;
import com.example.navire.mapper.ProjetMapper;
import com.example.navire.model.Projet;
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
        return projetMapper.toDTO(projetRepository.save(projet));
    }

    @Transactional
    public void deleteProjet(Long id) {
        if (!projetRepository.existsById(id)) {
            throw new ProjetNotFoundException(id);
        }
        projetRepository.deleteById(id);
    }
}
