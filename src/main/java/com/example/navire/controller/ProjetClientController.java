package com.example.navire.controller;

import com.example.navire.dto.ProjetClientDTO;
import com.example.navire.services.ProjetClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/projet-client")
public class ProjetClientController {
    @Autowired
    private ProjetClientService projetClientService;

    @PutMapping("/{id}/quantite-autorisee")
    public ResponseEntity<ProjetClientDTO> updateQuantiteAutorisee(@PathVariable Long id, @RequestBody Double quantiteAutorisee) {
        ProjetClientDTO updated = projetClientService.updateQuantiteAutorisee(id, quantiteAutorisee);
        return ResponseEntity.ok(updated);
    }
}
