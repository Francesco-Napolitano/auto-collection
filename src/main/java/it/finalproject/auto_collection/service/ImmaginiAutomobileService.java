package it.finalproject.auto_collection.service;

import it.finalproject.auto_collection.model.ImmagineAutomobile;
import it.finalproject.auto_collection.repo.ImmagineAutomobileRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ImmaginiAutomobileService {

    @Autowired
    private ImmagineAutomobileRepository repository;

    public List<ImmagineAutomobile> getImmaginiByAutoId(Long autoId){
        return repository.findByAutoId(autoId);
    }

}
