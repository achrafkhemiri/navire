package com.example.navire.controller;

import com.example.navire.dto.ProjetDTO;
import com.example.navire.services.ProjetService;
import com.example.navire.exception.ProjetNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.List;

@RestController
@RequestMapping("/api/projets")
public class ProjetController {
    @Autowired
    private ProjetService projetService;

    @GetMapping
    public List<ProjetDTO> getAllProjets() {
        return projetService.getAllProjets();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjetDTO> getProjetById(@PathVariable Long id) {
        return ResponseEntity.ok(projetService.getProjetById(id));
    }

    @PostMapping
    public ResponseEntity<ProjetDTO> createProjet(@RequestBody ProjetDTO dto) {
        ProjetDTO created = projetService.createProjet(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjetDTO> updateProjet(@PathVariable Long id, @RequestBody ProjetDTO dto) {
        ProjetDTO updated = projetService.updateProjet(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProjet(@PathVariable Long id) {
        projetService.deleteProjet(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler({ProjetNotFoundException.class})
    public ResponseEntity<String> handleException(Exception ex) {
        if (ex instanceof ProjetNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
