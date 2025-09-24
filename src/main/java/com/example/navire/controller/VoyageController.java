
package com.example.navire.controller;

import com.example.navire.dto.VoyageDTO;
import com.example.navire.services.VoyageService;
import com.example.navire.exception.VoyageNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.List;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/voyages")
public class VoyageController {
    @GetMapping("/projet/{projetId}")
    public ResponseEntity<List<VoyageDTO>> getVoyagesByProjet(@PathVariable Long projetId) {
        if (!voyageService.projetExists(projetId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        List<VoyageDTO> voyages = voyageService.getVoyagesByProjetId(projetId);
        return ResponseEntity.ok(voyages);
    }
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
    public ResponseEntity<?> createVoyage(@Valid @RequestBody VoyageDTO dto) {
        // Validation relations obligatoires côté controller
        StringBuilder errors = new StringBuilder();
        if (dto.getCamionId() == null) errors.append("Le champ camionId est obligatoire. ");
        if (dto.getChauffeurId() == null) errors.append("Le champ chauffeurId est obligatoire. ");
        if (dto.getClientId() == null) errors.append("Le champ clientId est obligatoire. ");
        if (dto.getDepotId() == null) errors.append("Le champ depotId est obligatoire. ");
        if (dto.getProjetId() == null) errors.append("Le champ projetId est obligatoire. ");
        if (dto.getUserId() == null) errors.append("Le champ userId est obligatoire. ");
        if (errors.length() > 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.toString());
        }
        VoyageDTO created = voyageService.createVoyage(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateVoyage(@PathVariable Long id, @Valid @RequestBody VoyageDTO dto) {
        // Validation relations obligatoires côté controller
        StringBuilder errors = new StringBuilder();
        if (dto.getCamionId() == null) errors.append("Le champ camionId est obligatoire. ");
        if (dto.getChauffeurId() == null) errors.append("Le champ chauffeurId est obligatoire. ");
        if (dto.getClientId() == null) errors.append("Le champ clientId est obligatoire. ");
        if (dto.getDepotId() == null) errors.append("Le champ depotId est obligatoire. ");
        if (dto.getProjetId() == null) errors.append("Le champ projetId est obligatoire. ");
        if (dto.getUserId() == null) errors.append("Le champ userId est obligatoire. ");
        if (errors.length() > 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.toString());
        }
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
