package it.finalproject.auto_collection.controller;

import it.finalproject.auto_collection.model.Auto;
import it.finalproject.auto_collection.service.AutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/auto")
public class AutoController {

    @Autowired
    private AutoService autoService;

    @GetMapping
    public List<Auto> getALlAutos(){
        return autoService.getAllAuto();
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Auto> getAutoById(@PathVariable Long id){
        return autoService.getAutoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/filtri")
    public List<Auto> getAutosFiltered(@RequestParam(required = false) Long brandID, @RequestParam (required = false) Long nazioneId,@RequestParam (required = false) String alimentazione,@RequestParam (required = false) String modello,@RequestParam (required = false) Integer anno,@RequestParam (required = false) BigDecimal prezzo,@RequestParam (required = false) String carrozzeria,@RequestParam (required = false) String unitaVendute) {
        return autoService.getFilteredAutos(brandID, nazioneId, alimentazione, modello, anno, prezzo, carrozzeria, unitaVendute);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Auto saveAuto (@RequestBody Auto auto){
        return autoService.saveAuto(auto);
    }

    //metodo UPDATE per modificare un auto esistente
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Auto> updatedAuto(@PathVariable Long id, @RequestBody Auto autoUpdated) {
        return autoService.updateAuto(id,autoUpdated).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteAuto(@PathVariable Long id){
        autoService.deleteAuto(id);
        return ResponseEntity.noContent().build();
    }



}
