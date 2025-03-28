package it.finalproject.auto_collection.controller;

import it.finalproject.auto_collection.model.Auto;
import it.finalproject.auto_collection.model.Nazione;
import it.finalproject.auto_collection.repo.AutoRepository;
import it.finalproject.auto_collection.repo.NazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nazioni")
@CrossOrigin(origins = "https://auto-collection-fe.vercel.app/")
public class NazioneController {

    @Autowired
    private NazioneRepository nazioneRepository;

    @Autowired
    private AutoRepository autoRepository;

    @GetMapping
    public List<Nazione> getAllNations(){
        return nazioneRepository.findAll();
    }


    // serve a ritornare tutte le automobili che appartengono a quella determinata nazione
    @GetMapping("/{nazioneId}/automobili")
    @PreAuthorize("isAuthenticated()")
    public List<Auto> getAutoByNation(@PathVariable Long nazioneId){
        return autoRepository.findByNazioneId(nazioneId);
    }
}
