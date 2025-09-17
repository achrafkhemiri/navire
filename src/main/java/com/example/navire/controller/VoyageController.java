package com.example.navire.controller;

import com.example.navire.dto.VoyageDTO;
import com.example.navire.services.VoyageService;
import com.example.navire.exception.VoyageNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.List;

@RestController
@RequestMapping("/api/voyages")
public class VoyageController {
    @Autowired
    private VoyageService voyageService;

    @GetMapping
    public List<VoyageDTO> getAllVoyages() {
        return voyageService.getAllVoyages();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VoyageDTO> getVoyageById(@PathVariable Long id) {
        return ResponseEntity.ok(voyageService.getVoyageById(id));
    }

    @PostMapping
    public ResponseEntity<VoyageDTO> createVoyage(@RequestBody VoyageDTO dto) {
        VoyageDTO created = voyageService.createVoyage(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VoyageDTO> updateVoyage(@PathVariable Long id, @RequestBody VoyageDTO dto) {
        VoyageDTO updated = voyageService.updateVoyage(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVoyage(@PathVariable Long id) {
        voyageService.deleteVoyage(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler({VoyageNotFoundException.class, IllegalArgumentException.class})
    public ResponseEntity<String> handleException(Exception ex) {
        if (ex instanceof VoyageNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
