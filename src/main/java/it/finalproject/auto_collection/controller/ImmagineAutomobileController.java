package it.finalproject.auto_collection.controller;

import it.finalproject.auto_collection.model.ImmagineAutomobile;
import it.finalproject.auto_collection.service.ImmaginiAutomobileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ImmagineAutomobileController {

    @Autowired
    private ImmaginiAutomobileService service;

    @GetMapping("immagini/auto/{autoId}")
    public List<ImmagineAutomobile> getImageById (Long autoId){
        return service.getImmaginiByAutoId(autoId);
    }
}
