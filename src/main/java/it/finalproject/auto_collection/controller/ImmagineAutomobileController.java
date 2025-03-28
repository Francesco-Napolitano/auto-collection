package it.finalproject.auto_collection.controller;

import it.finalproject.auto_collection.model.ImmagineAutomobile;
import it.finalproject.auto_collection.service.ImmagineAutomobileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin (origins = "https://auto-collection-fe.vercel.app/")
public class ImmagineAutomobileController {

    @Autowired
    private ImmagineAutomobileService service;

    @GetMapping("immagini/auto/{autoId}")
    public List<ImmagineAutomobile> getImageById (@PathVariable Long autoId){
        return service.getImmaginiByAutoId(autoId);
    }
}
