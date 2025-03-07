package it.finalproject.auto_collection.controller;

import it.finalproject.auto_collection.model.Auto;
import it.finalproject.auto_collection.model.Nazione;
import it.finalproject.auto_collection.repo.AutoRepository;
import it.finalproject.auto_collection.repo.NazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/nazioni")
public class NazioneController {

    @Autowired
    private NazioneRepository nazioneRepository;

    @Autowired
    private AutoRepository autoRepository;

    @GetMapping
    public List<Nazione> getAllNations(){
        return nazioneRepository.findAll();
    }

    @GetMapping("/{nazioneId}/automobili")
    @PreAuthorize("isAuthenticated()")
    public List<Auto> getAutoByNation(@PathVariable Long nazioneId){
        return autoRepository.findByNazioneId(nazioneId);
    }
}
