package it.finalproject.auto_collection.controller;

import it.finalproject.auto_collection.DTO.AutoDTO;
import it.finalproject.auto_collection.model.Auto;
import it.finalproject.auto_collection.service.AutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/auto")
@CrossOrigin(origins = "http://localhost:5173")
public class AutoController {

    @Autowired
    private AutoService autoService;

    @GetMapping
    public List<Auto> getALlAutos() {
        return autoService.getAllAuto();
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Auto> getAutoById(@PathVariable Long id) {
        return autoService.getAutoById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // metodo GET per ottenere automobili filtrate di vario tipo
    @GetMapping("/filtri")
    public List<Auto> getAutosFiltered(@RequestParam(required = false) Long brandID, @RequestParam(required = false) Long nazioneId, @RequestParam(required = false) String alimentazione, @RequestParam(required = false) String modello, @RequestParam(required = false) Integer annoMin, @RequestParam(required = false) BigDecimal prezzoMin, @RequestParam(required = false) String carrozzeria, @RequestParam(required = false) String unitaVendute) {
        return autoService.getFilteredAutos(brandID, nazioneId, alimentazione, modello, annoMin, prezzoMin, carrozzeria, unitaVendute);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Auto> createAuto(@RequestBody AutoDTO autoDTO) {
        Auto savedAuto = autoService.saveAuto(autoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAuto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updatedAuto(@PathVariable Long id, @RequestBody AutoDTO autoDTO) {
        System.out.println("JSON ricevuto: " + autoDTO);

        Auto updated = autoService.updateAuto(id, autoDTO);

        if (updated == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Errore: Auto con ID " + id + " non trovata");
        }

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteAuto(@PathVariable Long id) {
        autoService.deleteAuto(id);
        return ResponseEntity.noContent().build();
    }


}
